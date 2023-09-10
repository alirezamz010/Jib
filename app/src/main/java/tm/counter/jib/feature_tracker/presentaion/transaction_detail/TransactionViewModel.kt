package tm.counter.jib.feature_tracker.presentaion.transaction_detail

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.hamsaa.persiandatepicker.PersianDatePickerDialog
import ir.hamsaa.persiandatepicker.api.PersianPickerDate
import ir.hamsaa.persiandatepicker.api.PersianPickerListener
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import saman.zamani.persiandate.PersianDate
import tm.counter.jib.common.ArgumentKey
import tm.counter.jib.feature_tracker.domain.model.TransactionModel
import tm.counter.jib.feature_tracker.domain.use_case.TrackerUseCases
import java.util.Date
import javax.inject.Inject


@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val trackerUseCase: TrackerUseCases,
    savedStateHandle: SavedStateHandle,
    application: Application
) : AndroidViewModel(application) {

    private val context get() = getApplication<Application>()

    private val _state = MutableStateFlow(TransactionState())
    val state: StateFlow<TransactionState> = _state.asStateFlow()

    private var transactionId: Int =
        savedStateHandle.get<Int>(ArgumentKey.TRANSACTION_ID) ?: -1

    private var transactionDate: Long =
        savedStateHandle.get<Long>(ArgumentKey.TRANSACTION_DATE) ?: -1

    fun updateTitle(title: String) {
        _state.update { it.copy(title = title, enabled = true) }
    }

    init {

        if (transactionId != -1) {

            viewModelScope.launch {
                val transaction = trackerUseCase.getTransactionById(transactionId = transactionId)

                if (transaction != null) {

                    val amount = if (transaction.amount<0){
                        (transaction.amount *-1).toInt().toString()
                    }else{
                        transaction.amount.toInt().toString()
                    }

                    _state.update {
                        it.copy(
                            title = transaction.title,
                            amount = amount,
                            date = Date(transaction.time),
                            paymentType = if (transaction.paymentType == PaymentType.Card.key) {
                                PaymentType.Card
                            } else {
                                PaymentType.Cash
                            },
                            transactionType = if (transaction.amount < 0) {
                                TransactionType.Expense
                            } else {
                                TransactionType.Income
                            },
                            exist = true
                        )
                    }
                }

            }

        } else {
            _state.update { it.copy(date = Date(transactionDate)) }
        }

    }

    fun updateDate(activity: Context) {

        val picker: PersianDatePickerDialog =
            PersianDatePickerDialog(activity)
                .setPositiveButtonString("تایید")
                .setNegativeButton("بستن")
                .setTodayButtonVisible(false)
                .setMinYear(1300)
                .setInitDate(_state.value.date)
                .setShowInBottomSheet(false)
                .setTitleType(PersianDatePickerDialog.DAY_MONTH_YEAR)
                .setShowInBottomSheet(true)
                .setListener(object : PersianPickerListener {
                    override fun onDateSelected(pickedDate: PersianPickerDate) {

                        _state.update {
                            it.copy(
                                date = pickedDate.gregorianDate,
                                enabled = true
                            )
                        }
                    }

                    override fun onDismissed() {

                    }
                })

        picker.show()
    }

    fun updateAmount(amount: String) {
        _state.update {
            it.copy(
                amount = amount,
                enabled = true
            )
        }
    }

    fun updateTransactionType(transactionType: TransactionType) {
        _state.update {
            it.copy(
                transactionType = transactionType,
                enabled = true
            )
        }
    }

    fun updatePaymentType(paymentType: PaymentType) {
        _state.update {
            it.copy(
                paymentType = paymentType,
                enabled = true
            )
        }
    }

    fun deleteTransaction(navController: NavController) {
        viewModelScope.launch {
            trackerUseCase.deleteTransaction(transactionId)
        }
        navController.popBackStack()
    }

    fun saveTransaction(navController: NavController) {

        if (_state.value.amount.isEmpty()) {

            Toast.makeText(
                context,
                "لطفا مبلغ را وارد کنید",
                Toast.LENGTH_LONG
            ).show()

            return
        }

        if (_state.value.title.isEmpty()) {

            Toast.makeText(
                context,
                "لطفا عنوان را وارد کنید",
                Toast.LENGTH_LONG
            ).show()

            return
        }


        var amount = if (_state.value.amount.toDouble() > 0.0) {
            _state.value.amount.toDouble()
        } else {
            -1 * _state.value.amount.toDouble()
        }

        if (_state.value.transactionType == TransactionType.Expense) {
            amount *= -1
        }

        viewModelScope.launch {

            if (transactionId == -1) {
                trackerUseCase.insertTransaction(
                    TransactionModel(
                        title = state.value.title,
                        amount = amount,
                        time = state.value.date.time,
                        paymentType = state.value.paymentType.key
                    )
                )
            } else {
                trackerUseCase.insertTransaction(
                    TransactionModel(
                        id = transactionId,
                        title = state.value.title,
                        amount = amount,
                        time = state.value.date.time,
                        paymentType = state.value.paymentType.key
                    )
                )
            }

            navController.popBackStack()
        }


    }

}
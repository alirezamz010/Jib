package tm.counter.jib.feature_tracker.presentaion.transaction_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import tm.counter.jib.feature_tracker.domain.use_case.TrackerUseCases
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val trackerUseCase: TrackerUseCases,
) : ViewModel() {


    private val _state = MutableStateFlow(TransactionListState())
    val state: StateFlow<TransactionListState> = _state.asStateFlow()

    private var getTransactionsJob: Job? = null

    init {

        getTransactionList()

    }

    private fun getTransactionList() {
        getTransactionsJob?.cancel()
        getTransactionsJob =
            trackerUseCase.getTransactions().onEach { transactions ->
                _state.value = state.value.copy(
                    transactionList = transactions
                )
            }.launchIn(viewModelScope)
    }

    fun updateTime(day: Int) {

        val dayTime = day * (24L * 60L * 60L * 1000L)

        val time = _state.value.time

        _state.update { it.copy(time = time + dayTime) }

    }

    fun updateFilter(filter: ListFilter) {
        _state.update { it.copy(listFilter = filter) }
    }

}
package tm.counter.jib.feature_tracker.presentaion.transaction_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
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
    application: Application
) : AndroidViewModel(application) {

    private val context get() = getApplication<Application>()

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

        _state.update { it.copy(time = _state.value.time + dayTime) }

    }

    fun updateFilter(filter: ListFilter) {
        _state.update { it.copy(listFilter = filter) }
    }

}
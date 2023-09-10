package tm.counter.jib.feature_tracker.presentaion.transaction_list

import tm.counter.jib.feature_tracker.domain.model.TransactionModel

data class TransactionListState(
    val time: Long = System.currentTimeMillis(),
    val listFilter: ListFilter = ListFilter.All,
    val transactionList: List<TransactionModel> = listOf()
)
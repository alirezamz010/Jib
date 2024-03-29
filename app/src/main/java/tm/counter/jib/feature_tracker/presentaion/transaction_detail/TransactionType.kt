package tm.counter.jib.feature_tracker.presentaion.transaction_detail

sealed class TransactionType(
    val name: String,
) {

    object Income : TransactionType(
        name = "درآمد"
    )

    object Expense : TransactionType(
        name = "هزینه"
    )

}
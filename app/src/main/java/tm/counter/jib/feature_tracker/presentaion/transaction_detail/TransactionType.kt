package tm.counter.jib.feature_tracker.presentaion.transaction_detail

sealed class TransactionType(
    val name: String,
    val key: String
) {

    object Income : TransactionType(
        name = "درآمد",
        key = "income"
    )

    object Expense : TransactionType(
        name = "هزینه",
        key = "expense"
    )

}
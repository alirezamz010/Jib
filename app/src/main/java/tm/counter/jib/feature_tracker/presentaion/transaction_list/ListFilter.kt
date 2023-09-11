package tm.counter.jib.feature_tracker.presentaion.transaction_list

sealed class ListFilter(val name: String, key: String) {

    object All : ListFilter(name = "همه", key = "all")
    object Incomes : ListFilter(name = "درآمدها", key = "incomes")
    object Expenses : ListFilter(name = "هزینه ها", key = "expenses")

}

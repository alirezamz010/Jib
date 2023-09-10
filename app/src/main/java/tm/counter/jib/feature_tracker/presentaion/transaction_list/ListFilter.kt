package tm.counter.jib.feature_tracker.presentaion.transaction_list

sealed class ListFilter(val name: String, key: String) {

    object All : ListFilter(name = "نمایش همه", key = "all")
    object Incomes : ListFilter(name = "نمایش درآمدها", key = "incomes")
    object Expenses : ListFilter(name = "نمایش هزینه ها", key = "expenses")

}

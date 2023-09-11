package tm.counter.jib.feature_tracker.presentaion.transaction_list

sealed class ListFilter(val name: String) {

    object All : ListFilter(name = "همه")
    object Incomes : ListFilter(name = "درآمدها")
    object Expenses : ListFilter(name = "هزینه ها")

}

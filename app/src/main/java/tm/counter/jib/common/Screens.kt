package tm.counter.jib.common

sealed class Screens(val route: String) {

    object TransactionListScreen: Screens("transaction_list_screen")

    object TransactionScreen: Screens("transaction_screen")

}
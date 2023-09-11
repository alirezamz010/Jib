package tm.counter.jib.base

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tm.counter.jib.base.theme.Primary
import tm.counter.jib.common.ArgumentKey
import tm.counter.jib.common.Screens
import tm.counter.jib.feature_tracker.presentaion.transaction_detail.TransactionScreen
import tm.counter.jib.feature_tracker.presentaion.transaction_list.TransactionListScreen

@Composable
fun AppScreen(activity: Context) {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary),
    ) {

        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = Screens.TransactionListScreen.route
        ) {

            composable(route = Screens.TransactionListScreen.route) {
                TransactionListScreen(
                    navController = navController,
                )
            }

            composable(
                route = Screens.TransactionScreen.route +
                        "/{${ArgumentKey.TRANSACTION_ID}}&" +
                        "{${ArgumentKey.TRANSACTION_DATE}}",
                arguments = listOf(
                    navArgument(ArgumentKey.TRANSACTION_ID) { type = NavType.IntType },
                    navArgument(ArgumentKey.TRANSACTION_DATE) { type = NavType.LongType }
                )
            )
            {
                TransactionScreen(
                    navController = navController,
                    activity = activity
                )
            }

        }

    }

}
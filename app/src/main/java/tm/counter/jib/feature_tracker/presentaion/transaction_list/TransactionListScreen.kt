package tm.counter.jib.feature_tracker.presentaion.transaction_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import saman.zamani.persiandate.PersianDate
import tm.counter.jib.R
import tm.counter.jib.base.theme.ButtonColor
import tm.counter.jib.base.theme.Disabled
import tm.counter.jib.base.theme.ExpenseDark
import tm.counter.jib.base.theme.IncomeDark
import tm.counter.jib.base.theme.OnPrimary
import tm.counter.jib.base.theme.Outline
import tm.counter.jib.base.theme.Primary
import tm.counter.jib.base.theme.TextColor
import tm.counter.jib.common.Screens
import tm.counter.jib.feature_tracker.domain.model.TransactionModel
import tm.counter.jib.feature_tracker.presentaion.transaction_list.component.FilterItem
import tm.counter.jib.feature_tracker.presentaion.transaction_list.component.TransactionItem
import java.util.Calendar
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionListScreen(
    viewModel: TransactionListViewModel = hiltViewModel(),
    navController: NavController,
) {

    val state by viewModel.state.collectAsState()

    val calendar: Calendar = Calendar.getInstance()
    calendar.time = Date(state.time)
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    val start = calendar.timeInMillis
    val end = (start + 86400000L)


    val transactions: List<TransactionModel> = state.transactionList.filter { transaction ->

        when (state.listFilter) {

            is ListFilter.All -> {
                transaction.time in start..end
            }

            is ListFilter.Incomes -> {
                transaction.time in start..end && transaction.amount > 0
            }

            is ListFilter.Expenses -> {
                transaction.time in start..end && transaction.amount < 0
            }
        }

    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Primary)
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Spacer(modifier = Modifier.height(12.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {

                Spacer(modifier = Modifier.weight(1f))

                val today = System.currentTimeMillis()

                Card(
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    onClick = {
                        if (!(today in start..end)) {
                            viewModel.updateTime(1)
                        }
                    }
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_left_ios),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Outline),
                        modifier = Modifier
                            .size(32.dp)
                            .padding(8.dp)
                            .alpha(
                                if (today in start..end) {
                                    0.5f
                                } else {
                                    1f
                                }
                            )
                    )

                }

                val persianDate = PersianDate(state.time)

                Text(
                    text = "${persianDate.dayName()} ${persianDate.shDay} ${persianDate.monthName()}",
                    style = MaterialTheme.typography.bodyLarge.copy(textAlign = TextAlign.Center),
                    color = OnPrimary,
                    modifier = Modifier
                        .width(150.dp)
                        .padding(horizontal = 16.dp)
                )

                Card(
                    shape = MaterialTheme.shapes.extraLarge,
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                    onClick = {
                        viewModel.updateTime(-1)
                    }
                ) {

                    Image(
                        painter = painterResource(id = R.drawable.ic_arrow_left_ios),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(Outline),
                        modifier = Modifier
                            .rotate(180f)
                            .size(32.dp)
                            .padding(8.dp)
                    )

                }

                Spacer(modifier = Modifier.width(12.dp))

            }

            Spacer(modifier = Modifier.height(46.dp))

            Row(modifier = Modifier.padding(horizontal = 29.dp)) {

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_circle_up),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(IncomeDark),
                    modifier = Modifier
                        .padding(start = 9.dp)
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(17.dp))

                val incomes = state.transactionList.filter { transaction ->
                    transaction.time in start..end && transaction.amount > 0
                }.sumOf { it.amount }

                Text(text = "${incomes.toInt()}", color = OnPrimary)

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "مجموع درآمد ها", color = OnPrimary)

            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.padding(horizontal = 29.dp)) {

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_circle_up),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(ExpenseDark),
                    modifier = Modifier
                        .padding(start = 9.dp)
                        .rotate(180f)
                        .size(20.dp)
                )

                Spacer(modifier = Modifier.width(17.dp))

                val expenses = state.transactionList.filter { transaction ->
                    transaction.time in start..end && transaction.amount < 0
                }.sumOf { it.amount } * -1

                Text(text = "${expenses.toInt()}", color = OnPrimary)

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "مجموع هزینه ها", color = OnPrimary)
            }

            Spacer(modifier = Modifier.height(45.dp))

            Row {

                Spacer(modifier = Modifier.weight(1f))

                FilterItem(
                    filter = ListFilter.Expenses,
                    selected = ListFilter.Expenses == state.listFilter,
                    onClick = { viewModel.updateFilter(ListFilter.Expenses) }
                )

                Spacer(modifier = Modifier.width(8.dp))

                FilterItem(
                    filter = ListFilter.Incomes,
                    selected = ListFilter.Incomes == state.listFilter,
                    onClick = { viewModel.updateFilter(ListFilter.Incomes) }
                )

                Spacer(modifier = Modifier.width(8.dp))

                FilterItem(
                    filter = ListFilter.All,
                    selected = ListFilter.All == state.listFilter,
                    onClick = { viewModel.updateFilter(ListFilter.All) }
                )

                Spacer(modifier = Modifier.weight(1f))

            }

            Spacer(modifier = Modifier.height(26.dp))

            LazyColumn(
                Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(
                            topStart = 22.dp,
                            topEnd = 22.dp,
                        )
                    )
            ) {

                if (transactions.isEmpty()) {

                    item {
                        Column(
                            Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "موردی برای نمایش وجود ندارد",
                                modifier = Modifier.padding(vertical = 100.dp),
                                color = TextColor
                            )

                        }

                    }

                } else {

                    itemsIndexed(transactions) { index, transaction ->

                        Column(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navController.navigate(Screens.TransactionScreen.route + "/${transaction.id}&${state.time}")
                                }
                        ) {

                            TransactionItem(
                                transaction = transaction,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        bottom = 20.dp, top = if (index == 0) {
                                            38.dp
                                        } else {
                                            24.dp
                                        }
                                    )
                            )


                            if ((index + 1) != transactions.size) {

                                Divider(
                                    color = Disabled,
                                    modifier = Modifier
                                        .padding(horizontal = 16.dp)
                                        .alpha(0.25f)
                                )

                            }

                        }
                    }

                }

            }

        }

        FloatingActionButton(
            onClick = {
                navController.navigate(Screens.TransactionScreen.route + "/${-1}&${state.time}")
            },
            containerColor = ButtonColor,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "تراکنش جدید",
                    color = OnPrimary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 18.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(OnPrimary),
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))
            }

        }


    }

}
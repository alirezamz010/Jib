package tm.counter.jib.feature_tracker.presentaion.transaction_detail

import android.content.Context
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import tm.counter.jib.R
import tm.counter.jib.base.theme.ButtonColor
import tm.counter.jib.base.theme.OnPrimary
import tm.counter.jib.base.theme.Primary
import tm.counter.jib.base.theme.TextColor
import tm.counter.jib.feature_tracker.presentaion.transaction_detail.component.RadioCard
import tm.counter.jib.feature_tracker.presentaion.transaction_detail.component.TransactionAmountField
import tm.counter.jib.feature_tracker.presentaion.transaction_detail.component.TransactionDateField
import tm.counter.jib.feature_tracker.presentaion.transaction_detail.component.TransactionTitleField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionScreen(
    viewModel: TransactionViewModel = hiltViewModel(),
    navController: NavController,
    activity: Context
) {

    val state by viewModel.state.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Primary)
    ) {

        Row(
            modifier = Modifier
                .padding(start = 22.dp, end = 22.dp, top = 45.dp, bottom = 36.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                shape = RoundedCornerShape(800.dp),
                colors = CardDefaults.cardColors(containerColor = Primary),
                onClick = { navController.popBackStack() }
            ) {

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "img back",
                    modifier = Modifier.padding(12.dp)
                )

            }

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "افزودن تراکنش جدید",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

        }

        Card(
            shape = RoundedCornerShape(
                topStart = 22.dp,
                topEnd = 22.dp,
            ),
            colors = CardDefaults.cardColors(containerColor = OnPrimary),
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Spacer(modifier = Modifier.height(28.dp))


                TransactionTitleField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = state.title,
                    onValueChange = { viewModel.updateTitle(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "نوع تراکنش:",
                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                        color = TextColor,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioCard(
                        onClick = { viewModel.updateTransactionType(TransactionType.Income) },
                        modifier = Modifier.weight(1f),
                        title = TransactionType.Income.name,
                        selected = state.transactionType == TransactionType.Income,
                        painter = painterResource(id = R.drawable.ic_ouline_circle_arrow_up)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioCard(
                        onClick = { viewModel.updateTransactionType(TransactionType.Expense) },
                        modifier = Modifier.weight(1f),
                        title = TransactionType.Expense.name,
                        selected = state.transactionType == TransactionType.Expense,
                        painter = painterResource(id = R.drawable.ic_ouline_circle_arrow_down)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                TransactionAmountField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    value = state.amount,
                    onValueChange = { viewModel.updateAmount(it) }
                )

                Spacer(modifier = Modifier.height(16.dp))

                TransactionDateField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { viewModel.updateDate(activity) },
                    date = state.date
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row {

                    Spacer(modifier = Modifier.weight(1f))

                    Text(
                        text = "نوع پرداخت:",
                        style = MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.End),
                        color = TextColor,
                        modifier = Modifier.padding(horizontal = 24.dp)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    RadioCard(
                        onClick = { viewModel.updatePaymentType(PaymentType.Cash) },
                        modifier = Modifier.weight(1f),
                        title = PaymentType.Cash.name,
                        selected = state.paymentType == PaymentType.Cash,
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    RadioCard(
                        onClick = { viewModel.updatePaymentType(PaymentType.Card) },
                        modifier = Modifier.weight(1f),
                        title = PaymentType.Card.name,
                        selected = state.paymentType == PaymentType.Card,
                    )
                }

                Spacer(modifier = Modifier.height(50.dp))

                if (state.exist) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 50.dp, start = 16.dp, end = 16.dp)
                    ) {

                        OutlinedButton(
                            onClick = { viewModel.deleteTransaction(navController) },
                            modifier = Modifier
                                .weight(1f)
                        ) {

                            Box(modifier = Modifier.fillMaxWidth()) {

                                Text(
                                    text = "پاک کردن",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = ButtonColor,
                                    modifier = Modifier
                                        .padding(vertical = 15.dp)
                                        .align(Alignment.CenterEnd)
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.ic_delete),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(ButtonColor),
                                    modifier = Modifier
                                        .size(26.dp)
                                        .align(Alignment.CenterStart)
                                )

                            }

                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        Button(
                            onClick = { viewModel.saveTransaction(navController) },
                            colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                            enabled = state.enabled,
                            modifier = Modifier
                                .weight(1f)
                        ) {

                            Box(modifier = Modifier.fillMaxWidth()) {

                                Text(
                                    text = "ذخیره تغییرات",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = OnPrimary,
                                    modifier = Modifier
                                        .padding(vertical = 15.dp)
                                        .align(Alignment.CenterEnd)
                                )

                                Image(
                                    painter = painterResource(id = R.drawable.ic_edit),
                                    contentDescription = null,
                                    colorFilter = ColorFilter.tint(OnPrimary),
                                    modifier = Modifier
                                        .size(26.dp)
                                        .align(Alignment.CenterStart)
                                )

                            }
                        }

                    }

                } else {

                    Button(
                        onClick = { viewModel.saveTransaction(navController) },
                        colors = ButtonDefaults.buttonColors(containerColor = ButtonColor),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 30.dp)
                    ) {

                        Box(modifier = Modifier.fillMaxWidth()) {

                            Text(
                                text = "افزودن",
                                style = MaterialTheme.typography.titleSmall,
                                color = OnPrimary,
                                modifier = Modifier
                                    .padding(vertical = 15.dp)
                                    .align(Alignment.Center)
                            )

                            Image(
                                painter = painterResource(id = R.drawable.ic_add),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(OnPrimary),
                                modifier = Modifier
                                    .size(55.dp)
                                    .padding(15.dp)
                                    .align(Alignment.CenterStart)
                            )

                        }
                    }

                    Text(
                        text = "لغو",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Primary,
                        modifier = Modifier
                            .padding(16.dp)
                            .clickable { navController.popBackStack() }
                    )

                }


            }

        }

    }

}
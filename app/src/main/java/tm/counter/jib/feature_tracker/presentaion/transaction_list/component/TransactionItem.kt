package tm.counter.jib.feature_tracker.presentaion.transaction_list.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import tm.counter.jib.R
import tm.counter.jib.base.theme.Disabled
import tm.counter.jib.base.theme.ExpenseLight
import tm.counter.jib.base.theme.IncomeDark
import tm.counter.jib.base.theme.IncomeLight
import tm.counter.jib.base.theme.TextColor
import tm.counter.jib.feature_tracker.domain.model.TransactionModel

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: TransactionModel
) {

    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {

        Spacer(modifier = Modifier.width(17.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_left_ios),
            contentDescription = null,
            colorFilter = ColorFilter.tint(Disabled),
            modifier = Modifier.size(12.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = "تومان", color = Disabled)

        Spacer(modifier = Modifier.width(8.dp))

        val amount = if (transaction.amount < 0) {
            -transaction.amount
        } else {
            transaction.amount
        }

        Text(text = "${amount.toInt()}", color = TextColor)

        Spacer(modifier = Modifier.weight(1f))

        Text(text = transaction.title, color = TextColor)

        Spacer(modifier = Modifier.width(10.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_arrow_circle_up),
            contentDescription = null,
            colorFilter = ColorFilter.tint(
                if (transaction.amount < 0) {
                    ExpenseLight
                } else {
                    IncomeLight
                }
            ),
            modifier = Modifier
                .rotate(
                    if (transaction.amount < 0) {
                        180f
                    } else {
                        0f
                    }
                )
                .size(24.dp)
        )

        Spacer(modifier = Modifier.width(25.dp))
    }

}
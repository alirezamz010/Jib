package tm.counter.jib.feature_tracker.presentaion.transaction_detail.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import saman.zamani.persiandate.PersianDate
import tm.counter.jib.base.theme.Disabled
import tm.counter.jib.base.theme.SurfaceVariant
import tm.counter.jib.base.theme.TextColor
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionDateField(modifier: Modifier, onClick: () -> Unit, date: Date) {

    Card(
        colors = CardDefaults.cardColors(containerColor = SurfaceVariant),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick,
        modifier = modifier
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            val persianDate = PersianDate(date)

            Text(
                text = "${persianDate.dayName()} ${persianDate.shDay} ${persianDate.monthName()}",
                style = MaterialTheme.typography.headlineSmall,
                color = TextColor,
                modifier = Modifier.padding(vertical = 26.dp, horizontal = 24.dp)
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "تاریخ:",
                style = MaterialTheme.typography.headlineSmall,
                color = Disabled,
                modifier = Modifier.padding(vertical = 26.dp, horizontal = 14.dp)
            )

        }
    }

}
package tm.counter.jib.feature_tracker.presentaion.transaction_list.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import tm.counter.jib.base.theme.ButtonColor
import tm.counter.jib.base.theme.OnPrimary
import tm.counter.jib.base.theme.Outline
import tm.counter.jib.base.theme.Primary
import tm.counter.jib.feature_tracker.presentaion.transaction_list.ListFilter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilterItem(
    modifier: Modifier = Modifier,
    filter: ListFilter,
    selected: Boolean,
    onClick: () -> Unit
) {

    Card(
        shape = if (selected) {
            MaterialTheme.shapes.extraLarge
        } else {
            MaterialTheme.shapes.small
        },
        border = if (!selected) BorderStroke(1.dp, Outline) else null,
        colors = CardDefaults.cardColors(
            containerColor = if (selected) {
                ButtonColor
            } else {
                Primary
            }
        ),
        onClick = onClick
    ) {

        Text(
            text = filter.name,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            color = OnPrimary
        )

    }

}
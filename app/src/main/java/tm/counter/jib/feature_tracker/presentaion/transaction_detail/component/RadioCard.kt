package tm.counter.jib.feature_tracker.presentaion.transaction_detail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import tm.counter.jib.base.theme.ButtonColor
import tm.counter.jib.base.theme.Disabled
import tm.counter.jib.base.theme.Outline
import tm.counter.jib.base.theme.SurfaceVariant
import tm.counter.jib.base.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioCard(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    title: String,
    painter: Painter? = null,
    selected: Boolean,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = SurfaceVariant
        ),
        onClick = onClick,
        modifier = if (selected) {
            modifier.border(
                width = 2.dp,
                color = ButtonColor,
                shape = MaterialTheme.shapes.medium
            )
        } else {
            modifier
        }
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Spacer(modifier = Modifier.width(24.dp))

            RadioButton(
                modifier = Modifier.size(10.dp),
                selected = selected,
                colors = RadioButtonDefaults.colors(selectedColor = ButtonColor, unselectedColor = Outline),
                onClick = onClick
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                color = if (selected) {
                    Color.Black
                } else {
                    Disabled
                },
                modifier = Modifier.padding(top = 24.dp, bottom = 24.dp, end = 10.dp)
            )

            if (painter != null) {
                Image(
                    painter = painter,
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(
                        if (selected){
                            Color.Black
                        }else{
                            Disabled
                        }
                    ),
                    modifier = Modifier
                        .size(35.dp)
                        .padding(end = 16.dp)
                )
            }else{
                Spacer(modifier = Modifier.width(12.dp))
            }

        }
    }
}

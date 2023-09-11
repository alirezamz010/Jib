package tm.counter.jib.feature_tracker.presentaion.transaction_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import tm.counter.jib.base.theme.Disabled
import tm.counter.jib.base.theme.SurfaceVariant

@Composable
fun TransactionPlaceField(modifier: Modifier, value: String, onValueChange: (String) -> Unit) {

    Row(modifier) {
        Row(
            modifier = Modifier.background(
                color = SurfaceVariant,
                shape = MaterialTheme.shapes.medium
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                ),
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )

            Text(
                text = "محل تراکنش:",
                style = MaterialTheme.typography.headlineSmall,
                color = Disabled,
                modifier = Modifier.padding(bottom = 26.dp, top = 26.dp, end = 14.dp, start = 8.dp)
            )

        }
    }


}
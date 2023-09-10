package tm.counter.jib.feature_tracker.presentaion.transaction_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.dp
import tm.counter.jib.base.theme.Disabled
import tm.counter.jib.base.theme.SurfaceVariant
import tm.counter.jib.base.theme.TextColor

@Composable
fun TransactionAmountField(modifier: Modifier, value: String, onValueChange: (String) -> Unit) {

    Row(
        modifier = modifier,
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = SurfaceVariant,
                    shape = MaterialTheme.shapes.medium
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "تومان",
                style = MaterialTheme.typography.headlineSmall,
                color = Disabled,
                modifier = Modifier.padding(
                    top = 26.dp,
                    bottom = 26.dp,
                    start = 24.dp,
                )
            )

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.NumberPassword
                ),
                textStyle = MaterialTheme.typography.headlineSmall.copy(
                    textDirection = TextDirection.Ltr,
                    textAlign = TextAlign.Start
                ),
                singleLine = true,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            )


            Text(
                text = "مبلغ:",
                style = MaterialTheme.typography.headlineSmall,
                color = Disabled,
                modifier = Modifier.padding(end = 26.dp, top = 14.dp, bottom = 14.dp)
            )

        }


    }

}
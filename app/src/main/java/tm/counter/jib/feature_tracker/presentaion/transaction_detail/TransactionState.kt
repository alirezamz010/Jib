package tm.counter.jib.feature_tracker.presentaion.transaction_detail

import java.util.Date

data class TransactionState(
    val exist: Boolean = false,
    val enabled: Boolean = false,
    val title: String = "",
    val place: String = "",
    val amount: String = "",
    val date: Date = Date(System.currentTimeMillis()),
    val transactionType: TransactionType = TransactionType.Income,
    val paymentType: PaymentType = PaymentType.Card
)
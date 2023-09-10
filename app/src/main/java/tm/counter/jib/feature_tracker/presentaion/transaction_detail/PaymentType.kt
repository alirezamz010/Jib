package tm.counter.jib.feature_tracker.presentaion.transaction_detail

sealed class PaymentType(
    val name: String,
    val key: String
) {

    object Card : PaymentType(
        name = "کارتی",
        key = "card"
    )

    object Cash : PaymentType(
        name = "نقدی",
        key = "cash"
    )

}
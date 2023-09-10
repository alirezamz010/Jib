package tm.counter.jib.feature_tracker.domain.use_case

data class TrackerUseCases(
    val insertTransaction: InsertTransactionUseCase,
    val deleteTransaction: DeleteTransactionUseCase,
    val getTransactions: GetTransactionsUseCase,
    val getTransactionById: GetTransactionByIdUseCase
)

package tm.counter.jib.feature_tracker.domain.use_case

import tm.counter.jib.common.InvalidException
import tm.counter.jib.feature_tracker.domain.model.TransactionModel
import tm.counter.jib.feature_tracker.domain.repository.TransactionRepository


class InsertTransactionUseCase(private val repository: TransactionRepository) {

    @Throws(InvalidException::class)
    suspend operator fun invoke(transactionModel: TransactionModel) {

        repository.insertTransaction(transactionModel)
    }

}
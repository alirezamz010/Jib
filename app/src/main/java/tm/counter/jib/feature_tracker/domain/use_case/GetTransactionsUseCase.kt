package tm.counter.jib.feature_tracker.domain.use_case

import kotlinx.coroutines.flow.Flow
import tm.counter.jib.common.InvalidException
import tm.counter.jib.feature_tracker.domain.model.TransactionModel
import tm.counter.jib.feature_tracker.domain.repository.TransactionRepository


class GetTransactionsUseCase(private val repository: TransactionRepository) {

    @Throws(InvalidException::class)
    operator fun invoke(): Flow<List<TransactionModel>> {

        return repository.getTransactions()
    }

}
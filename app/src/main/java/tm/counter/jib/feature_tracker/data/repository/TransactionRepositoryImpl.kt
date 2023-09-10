package tm.counter.jib.feature_tracker.data.repository

import kotlinx.coroutines.flow.Flow
import tm.counter.jib.feature_tracker.data.data_source.TransactionDao
import tm.counter.jib.feature_tracker.domain.model.TransactionModel
import tm.counter.jib.feature_tracker.domain.repository.TransactionRepository

class TransactionRepositoryImpl(
    private val dao: TransactionDao
) : TransactionRepository {

    override suspend fun insertTransaction(transactionModel: TransactionModel) {
        return dao.insert(transactionModel)
    }

    override suspend fun deleteTransaction(id: Int) {
        return dao.delete(id)
    }

    override fun getTransactions(): Flow<List<TransactionModel>> {
        return dao.getItems()
    }

    override suspend fun getTransactionById(id: Int): TransactionModel? {
        return dao.getItemById(id)
    }

}
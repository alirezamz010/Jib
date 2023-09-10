package tm.counter.jib.feature_tracker.domain.repository

import kotlinx.coroutines.flow.Flow
import tm.counter.jib.feature_tracker.domain.model.TransactionModel

interface TransactionRepository {

    suspend fun insertTransaction(transactionModel: TransactionModel)

    suspend fun deleteTransaction(id: Int)

    fun getTransactions(): Flow<List<TransactionModel>>

    suspend fun getTransactionById(id: Int): TransactionModel?

}
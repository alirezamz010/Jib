package tm.counter.jib.feature_tracker.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import tm.counter.jib.feature_tracker.domain.model.TransactionModel

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transactionModel: TransactionModel)

    @Query("DELETE from ${TransactionDatabase.DATABASE_NAME} WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * from ${TransactionDatabase.DATABASE_NAME}")
    fun getItems(): Flow<List<TransactionModel>>

    @Query("SELECT * from ${TransactionDatabase.DATABASE_NAME} WHERE id = :id")
    suspend fun getItemById(id: Int): TransactionModel?

}
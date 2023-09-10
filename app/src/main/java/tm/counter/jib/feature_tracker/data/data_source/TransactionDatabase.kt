package tm.counter.jib.feature_tracker.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import tm.counter.jib.feature_tracker.domain.model.TransactionModel


@Database(
    entities = [TransactionModel::class],
    version = 1
)
abstract class TransactionDatabase: RoomDatabase() {

    abstract val transactionDao: TransactionDao

    companion object {
        const val DATABASE_NAME = "transactions_db"
    }

}
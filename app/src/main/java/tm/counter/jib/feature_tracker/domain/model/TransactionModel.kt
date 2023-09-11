package tm.counter.jib.feature_tracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import tm.counter.jib.feature_tracker.data.data_source.TransactionDatabase

@Entity(tableName = TransactionDatabase.DATABASE_NAME)
data class TransactionModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val place: String,
    val amount: Double,
    val paymentType: String,
    val time: Long
)
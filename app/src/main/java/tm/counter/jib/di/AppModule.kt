package tm.counter.jib.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import tm.counter.jib.feature_tracker.data.data_source.TransactionDatabase
import tm.counter.jib.feature_tracker.data.repository.TransactionRepositoryImpl
import tm.counter.jib.feature_tracker.domain.repository.TransactionRepository
import tm.counter.jib.feature_tracker.domain.use_case.DeleteTransactionUseCase
import tm.counter.jib.feature_tracker.domain.use_case.GetTransactionByIdUseCase
import tm.counter.jib.feature_tracker.domain.use_case.GetTransactionsUseCase
import tm.counter.jib.feature_tracker.domain.use_case.InsertTransactionUseCase
import tm.counter.jib.feature_tracker.domain.use_case.TrackerUseCases
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideTransactionsDatabase(app: Application): TransactionDatabase {
        return Room
            .databaseBuilder(
                app,
                TransactionDatabase::class.java,
                TransactionDatabase.DATABASE_NAME
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionRepository(db: TransactionDatabase): TransactionRepository {
        return TransactionRepositoryImpl(db.transactionDao)
    }


    @Provides
    @Singleton
    fun provideTrackerUseCases(
        transactionRepository: TransactionRepository
    ): TrackerUseCases {
        return TrackerUseCases(
            insertTransaction = InsertTransactionUseCase(transactionRepository),
            deleteTransaction = DeleteTransactionUseCase(transactionRepository),
            getTransactions = GetTransactionsUseCase(transactionRepository),
            getTransactionById = GetTransactionByIdUseCase(transactionRepository),
        )
    }

}
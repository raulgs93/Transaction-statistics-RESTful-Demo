package com.raulgs93.statistics.application

import com.raulgs93.statistics.application.SaveTransactionStatus.OldTransaction
import com.raulgs93.statistics.application.SaveTransactionStatus.RecentTransaction
import com.raulgs93.statistics.domain.transaction.Transaction
import com.raulgs93.statistics.domain.transaction.TransactionRepository
import com.raulgs93.statistics.domain.transaction.factory.AmountFactory
import com.raulgs93.statistics.domain.transaction.factory.TimestampFactory
import java.time.Instant

class SaveTransaction(private val transactionRepository: TransactionRepository) {

    operator fun invoke(saveTransactionRequest: SaveTransactionRequest): SaveTransactionStatus {
        val amount = AmountFactory.create(saveTransactionRequest.amount)
        val timestamp = TimestampFactory.create(saveTransactionRequest.timestamp)

        transactionRepository.save(Transaction(amount, timestamp))

        val secondsDiff: Long = Instant.now().epochSecond - timestamp.value.epochSecond
        return if (secondsDiff > SIXTY_SECONDS) OldTransaction
        else RecentTransaction
    }
}

data class SaveTransactionRequest(val amount: String, val timestamp: String)

sealed class SaveTransactionStatus {
    object RecentTransaction : SaveTransactionStatus()
    object OldTransaction : SaveTransactionStatus()
}
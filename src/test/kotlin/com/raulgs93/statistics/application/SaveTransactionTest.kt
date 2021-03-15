package com.raulgs93.statistics.application

import com.raulgs93.statistics.infrastructure.memory.InMemoryTransactionRepository
import com.raulgs93.statistics.utils.getTransaction
import com.raulgs93.statistics.utils.toDateString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant

internal class SaveTransactionTest {

    @Test
    fun `return old transaction if is older than 60 seconds`() {
        val repository = InMemoryTransactionRepository()
        val saveTransaction = SaveTransaction(repository)

        val instant = Instant.now().minusSeconds(61L)

        val actualRequest = SaveTransactionRequest("1.253", instant.toDateString())

        print(instant.toDateString())

        val actual = saveTransaction(actualRequest)

        Assertions.assertEquals(getTransaction(instant = instant), repository.listAll().first())
        Assertions.assertEquals(SaveTransactionStatus.OldTransaction, actual)
    }

    @Test
    fun `return new transaction if is not older than 60 seconds`() {
        val repository = InMemoryTransactionRepository()
        val saveTransaction = SaveTransaction(repository)

        val instant = Instant.now().minusSeconds(60L)

        val actualRequest = SaveTransactionRequest("1.253", instant.toDateString())

        print(instant.toDateString())

        val actual = saveTransaction(actualRequest)

        Assertions.assertEquals(getTransaction(instant = instant), repository.listAll().first())
        Assertions.assertEquals(SaveTransactionStatus.RecentTransaction, actual)
    }


}
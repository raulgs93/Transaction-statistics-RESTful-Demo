package com.raulgs93.statistics.application

import com.raulgs93.statistics.infrastructure.memory.InMemoryTransactionRepository
import com.raulgs93.statistics.utils.getTransaction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class DeleteTransactionsTest {

    @Test
    fun `should delete everything from the list`() {
        val repository = InMemoryTransactionRepository()
        val deleteTransactions = DeleteTransactions(repository)

        repository.save(getTransaction())
        Assertions.assertFalse(repository.listAll().isEmpty())

        deleteTransactions()
        Assertions.assertTrue(repository.listAll().isEmpty())
    }
}
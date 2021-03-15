package com.raulgs93.statistics.infrastructure.memory

import com.raulgs93.statistics.domain.transaction.Transaction
import com.raulgs93.statistics.domain.transaction.TransactionRepository

class InMemoryTransactionRepository : TransactionRepository {

    private var transactionList: MutableList<Transaction> = mutableListOf()

    override fun save(transaction: Transaction) {
        transactionList.add(transaction)
    }

    override fun deleteAll() {
        transactionList.clear()
    }

    override fun listAll() = transactionList
}
package com.raulgs93.statistics.application

import com.raulgs93.statistics.domain.transaction.Transaction
import com.raulgs93.statistics.domain.transaction.TransactionRepository

class GetTransactions(private val transactionRepository: TransactionRepository) {

    operator fun invoke(): List<Transaction> {
        return transactionRepository.listAll()
    }

}
package com.raulgs93.statistics.application

import com.raulgs93.statistics.domain.transaction.TransactionRepository


class DeleteTransactions(private val transactionRepository: TransactionRepository) {

    operator fun invoke() {
        transactionRepository.deleteAll()
    }

}
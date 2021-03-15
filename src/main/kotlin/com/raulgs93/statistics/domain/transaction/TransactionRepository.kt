package com.raulgs93.statistics.domain.transaction

interface TransactionRepository {

    fun save(transaction: Transaction)

    fun deleteAll()

    fun listAll(): List<Transaction>

}

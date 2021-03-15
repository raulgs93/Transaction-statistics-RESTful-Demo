package com.raulgs93.statistics.infrastructure.framework.configuration

import com.raulgs93.statistics.application.DeleteTransactions
import com.raulgs93.statistics.application.GetStatistics
import com.raulgs93.statistics.application.GetTransactions
import com.raulgs93.statistics.application.SaveTransaction
import com.raulgs93.statistics.infrastructure.memory.InMemoryTransactionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class controllerConfiguration {

    @Bean
    fun transactionRepository(): InMemoryTransactionRepository {
        return InMemoryTransactionRepository()
    }

    @Bean
    fun saveTransaction(inMemoryTransactionRepository: InMemoryTransactionRepository): SaveTransaction {
        return SaveTransaction(inMemoryTransactionRepository)
    }

    @Bean
    fun getTransactions(inMemoryTransactionRepository: InMemoryTransactionRepository): GetTransactions {
        return GetTransactions(inMemoryTransactionRepository)
    }

    @Bean
    fun deleteTransactions(inMemoryTransactionRepository: InMemoryTransactionRepository): DeleteTransactions {
        return DeleteTransactions(inMemoryTransactionRepository)
    }

    @Bean
    fun getStatistics(inMemoryTransactionRepository: InMemoryTransactionRepository): GetStatistics {
        return GetStatistics(inMemoryTransactionRepository)
    }

    /*
    @Bean
    fun transactionController(saveTransaction: SaveTransaction) : TransactionController {
        return TransactionController(saveTransaction)
    }
    */
}
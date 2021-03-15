package com.raulgs93.statistics.infrastructure.framework.controller

import com.raulgs93.statistics.application.DeleteTransactions
import com.raulgs93.statistics.application.GetTransactions
import com.raulgs93.statistics.application.SaveTransaction
import com.raulgs93.statistics.application.SaveTransactionRequest
import com.raulgs93.statistics.application.SaveTransactionStatus.OldTransaction
import com.raulgs93.statistics.application.SaveTransactionStatus.RecentTransaction
import com.raulgs93.statistics.domain.transaction.Transaction
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("$URL_BASE/transactions")
class TransactionController(
    val saveTransaction: SaveTransaction,
    val getTransactions: GetTransactions,
    val deleteTransactions: DeleteTransactions
) {

    

    @PostMapping
    fun saveTransaction(@RequestBody transactionRequest: TransactionRequest): ResponseEntity<Any> {

        val status = saveTransaction(
            SaveTransactionRequest(
                transactionRequest.amount,
                transactionRequest.timestamp
            )
        )

        return when (status) {
            is RecentTransaction -> ResponseEntity(HttpStatus.CREATED)
            is OldTransaction -> ResponseEntity(HttpStatus.NO_CONTENT)
        }

    }


    @DeleteMapping("")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun clearTransactions() {
        deleteTransactions()
    }
}

data class TransactionRequest(val amount: String, val timestamp: String)
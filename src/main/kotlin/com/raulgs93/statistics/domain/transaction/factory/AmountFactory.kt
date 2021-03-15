package com.raulgs93.statistics.domain.transaction.factory

import com.raulgs93.statistics.domain.transaction.Amount
import com.raulgs93.statistics.domain.transaction.AmountFormatException

object AmountFactory {
    fun create(amount: String): Amount {
        return try {
            Amount(amount.toBigDecimal())
        } catch (e: NumberFormatException) {
            throw AmountFormatException("Unable to cast amount to bigDecimal")
        }
    }
}
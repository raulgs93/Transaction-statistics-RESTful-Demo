package com.raulgs93.statistics.utils

import com.raulgs93.statistics.application.StatisticsResponse
import com.raulgs93.statistics.domain.transaction.Amount
import com.raulgs93.statistics.domain.transaction.Timestamp
import com.raulgs93.statistics.domain.transaction.Transaction
import java.math.BigDecimal
import java.time.Instant

fun getTransaction(
    amount: String = "1.253",
    instant: Instant = Instant.now(),
) = Transaction(
    Amount(amount.toBigDecimal()),
    Timestamp(instant)
)

fun getStatisticsResponse(
    sum: BigDecimal = "2.51".toBigDecimal(),
    avg: BigDecimal = "1.25".toBigDecimal(),
    max: BigDecimal = "1.25".toBigDecimal(),
    min: BigDecimal = "1.25".toBigDecimal(),
    count: Long = 2L
) = StatisticsResponse(sum, avg, max, min, count)
package com.raulgs93.statistics.application

import com.raulgs93.statistics.domain.transaction.TransactionRepository
import java.math.BigDecimal
import java.time.Instant

class GetStatistics(private val transactionRepository: TransactionRepository) {

    operator fun invoke(): StatisticsResponse {
        val transactions = transactionRepository.listAll()

        val newTransactions = transactions.filter {
            val secondsDiff: Long = (Instant.now().epochSecond - it.timestamp.value.epochSecond)
            secondsDiff <= SIXTY_SECONDS
        }

        val transactionCount = newTransactions.count()

        val transactionSum = newTransactions.sumOf { it.amount.value }

        val transactionAvg = average(transactionSum, transactionCount)

        val transactionMax = newTransactions.maxOfOrNull { it.amount.value } ?: BigDecimal.ZERO

        val transactionMin = newTransactions.minOfOrNull { it.amount.value } ?: BigDecimal.ZERO

        return StatisticsResponse(
            sum = transactionSum.roundHalfUp(),
            avg = transactionAvg.roundHalfUp(),
            max = transactionMax.roundHalfUp(),
            min = transactionMin.roundHalfUp(),
            count = transactionCount.toLong()
        )
    }

    private fun average(sum: BigDecimal, count: Int): BigDecimal {
        return if (count == 0) BigDecimal.ZERO
        else sum.div(BigDecimal(count))
    }
}

data class StatisticsResponse(
    val sum: BigDecimal,
    val avg: BigDecimal,
    val max: BigDecimal,
    val min: BigDecimal,
    val count: Long
)

private fun BigDecimal.roundHalfUp(): BigDecimal {
    return this.setScale(2, BigDecimal.ROUND_HALF_UP)
}
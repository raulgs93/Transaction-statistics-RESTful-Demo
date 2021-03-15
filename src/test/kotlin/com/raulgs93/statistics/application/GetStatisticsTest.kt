package com.raulgs93.statistics.application

import com.raulgs93.statistics.infrastructure.memory.InMemoryTransactionRepository
import com.raulgs93.statistics.utils.getStatisticsResponse
import com.raulgs93.statistics.utils.getTransaction
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.Instant

internal class GetStatisticsTest {

    @Test
    fun `return statistics for transactions form last 60 seconds`() {
        val repository = mockk<InMemoryTransactionRepository>()
        val getStatistics = GetStatistics(repository)

        every { repository.listAll() } returns mutableListOf(
            getTransaction(), getTransaction(), getTransaction(instant = Instant.now().minusSeconds(61L))
        )

        val actual = getStatistics()
        val expected = getStatisticsResponse()

        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `return empty if there are no transactions in last 60 seconds`() {
        val repository = mockk<InMemoryTransactionRepository>()
        val getStatistics = GetStatistics(repository)

        every { repository.listAll() } returns mutableListOf(
            getTransaction(instant = Instant.now().minusSeconds(61L))
        )

        val actual = getStatistics()
        val expected = getStatisticsResponse(
            "0.00".toBigDecimal(),
            "0.00".toBigDecimal(),
            "0.00".toBigDecimal(),
            "0.00".toBigDecimal(),
            0L
        )

        Assertions.assertEquals(expected, actual)
    }


}



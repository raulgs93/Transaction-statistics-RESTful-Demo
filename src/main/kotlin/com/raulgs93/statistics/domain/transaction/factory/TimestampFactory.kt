package com.raulgs93.statistics.domain.transaction.factory

import com.raulgs93.statistics.domain.transaction.DateFormatException
import com.raulgs93.statistics.domain.transaction.InvalidDateException
import com.raulgs93.statistics.domain.transaction.Timestamp
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

object TimestampFactory {
    fun create(timestamp: String): Timestamp {
        return try {
            val transactionDate = LocalDateTime.parse(
                timestamp,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").withZone(ZoneId.of("UTC"))
            ).toInstant(ZoneOffset.UTC)

            val currentDate = Instant.now()

            if (transactionDate.isAfter(currentDate)) {
                throw InvalidDateException("Invalid date")
            }

            Timestamp(transactionDate)
        } catch (e: Exception) {
            throw DateFormatException("Unable to cast date to ISO 8601 UTC format")
        }
    }
}
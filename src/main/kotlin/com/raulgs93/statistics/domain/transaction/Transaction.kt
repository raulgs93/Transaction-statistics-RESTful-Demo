package com.raulgs93.statistics.domain.transaction

import java.math.BigDecimal
import java.time.Instant

data class Transaction(val amount: Amount, val timestamp: Timestamp)

data class Amount(val value: BigDecimal)

data class Timestamp(val value: Instant)
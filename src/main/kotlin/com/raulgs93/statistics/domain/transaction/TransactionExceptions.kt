package com.raulgs93.statistics.domain.transaction

open class TransactionFormatException(message: String) : RuntimeException(message)

class AmountFormatException(message: String) : TransactionFormatException(message)

class DateFormatException(message: String) : TransactionFormatException(message)

class InvalidDateException(message: String) : TransactionFormatException(message)

class InvalidJSONException(message: String) : RuntimeException(message)


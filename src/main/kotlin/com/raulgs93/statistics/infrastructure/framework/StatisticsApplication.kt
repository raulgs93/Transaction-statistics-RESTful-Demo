package com.raulgs93.statistics.infrastructure.framework

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StatisticsApplication

fun main(args: Array<String>) {
    runApplication<StatisticsApplication>(*args)
}

package com.raulgs93.statistics.infrastructure.framework.controller

import com.raulgs93.statistics.application.GetStatistics
import com.raulgs93.statistics.application.StatisticsResponse
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("$URL_BASE/statistics")
class StatisticsController(val getStatistics: GetStatistics) {

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun fetchStatistics(): StatisticsResponse {
        return getStatistics()
    }
}
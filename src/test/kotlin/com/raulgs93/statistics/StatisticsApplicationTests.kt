package com.raulgs93.statistics

import com.raulgs93.statistics.infrastructure.framework.StatisticsApplication
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [StatisticsApplication::class])
class StatisticsApplicationTests {

    @Test
    fun contextLoads() {
    }

}

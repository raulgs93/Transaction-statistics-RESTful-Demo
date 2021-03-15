package com.raulgs93.statistics.infrastructure.framework.controller

import com.raulgs93.statistics.infrastructure.memory.InMemoryTransactionRepository
import com.raulgs93.statistics.utils.getTransaction
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.skyscreamer.jsonassert.JSONCompareMode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class StatisticsControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var repository: InMemoryTransactionRepository

    @Test
    fun `return statistics for transactions form last 60 seconds`() {
        repository.save(getTransaction())
        repository.save(getTransaction())

        val response = restTemplate.getForEntity("$URL_BASE/statistics", String::class.java)
        val expectedResponse = StatisticsControllerTest::class.java.getResource("/responses/statistics.json").readText()

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.OK, response.statusCode)

        JSONAssert.assertEquals(expectedResponse, response.body, JSONCompareMode.LENIENT)
    }
}
package com.raulgs93.statistics.infrastructure.framework.controller


import com.raulgs93.statistics.infrastructure.memory.InMemoryTransactionRepository
import com.raulgs93.statistics.utils.getTransaction
import com.raulgs93.statistics.utils.toDateString
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import java.time.Instant

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class TransactionControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    @Autowired
    lateinit var repository: InMemoryTransactionRepository

    @Test
    fun `return 201 if transaction timestamp is within last 60 seconds`() {
        repository.save(getTransaction())

        val request = HttpEntity(TransactionRequest("123.456", Instant.now().toDateString()))
        val response = restTemplate.postForEntity("$URL_BASE/transactions", request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.CREATED, response.statusCode)
    }

    @Test
    fun `return 204 if transaction timestamp is not within last 60 seconds`() {
        repository.save(getTransaction())

        val request = HttpEntity(TransactionRequest("123.456", Instant.now().minusSeconds(61L).toDateString()))
        val response = restTemplate.postForEntity("$URL_BASE/transactions", request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.statusCode)
    }

    @Test
    fun `return 424 if amount is not parseable to bigdecimal`() {
        repository.save(getTransaction())

        val request = HttpEntity(TransactionRequest("123A56", Instant.now().minusSeconds(61L).toDateString()))
        val response = restTemplate.postForEntity("$URL_BASE/transactions", request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.statusCode)
    }

    @Test
    fun `return 424 if timestamp is not parseable`() {
        repository.save(getTransaction())

        val request = HttpEntity(TransactionRequest("123.56", "2021-14-03"))
        val response = restTemplate.postForEntity("$URL_BASE/transactions", request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.statusCode)
    }

    @Test
    fun `return 424 if timestamp is in the future`() {
        repository.save(getTransaction())

        val request = HttpEntity(TransactionRequest("123.56", Instant.now().plusSeconds(61L).toDateString()))
        val response = restTemplate.postForEntity("$URL_BASE/transactions", request, String::class.java)

        Assertions.assertNotNull(response)
        Assertions.assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, response.statusCode)
    }
}
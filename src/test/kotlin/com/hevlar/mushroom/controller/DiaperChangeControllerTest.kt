package com.hevlar.mushroom.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hevlar.mushroom.controller.dto.ChildDto
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import com.hevlar.mushroom.controller.dto.DiaperChangeDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.DiaperChangeRecording
import com.hevlar.mushroom.model.DiaperStatus
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.LocalDateTime
import org.slf4j.LoggerFactory

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class DiaperChangeControllerTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private val logger = LoggerFactory.getLogger(DiaperChangeControllerTest::class.java)

    lateinit var child: Child

    @BeforeAll
    fun setup() {
        val childDto = ChildDto("John", LocalDate.of(2020,1, 1))
        val json = objectMapper.writeValueAsString(childDto)

        val result = mockMvc.perform(post("/children")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated)
            .andReturn()

        child = objectMapper.readValue(result.response.contentAsString, Child::class.java)
        logger.info("Created child with id = ${child.id}")
    }

    @Test
    @Order(1)
    fun `should add diaper change successfully`() {
        val diaperChangeDto = DiaperChangeDto(LocalDateTime.now(), DiaperStatus.Clean)
        val json = objectMapper.writeValueAsString(diaperChangeDto)

        val result = mockMvc.perform(post("/children/${child.id}/diaper")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val diaperChangeRecording = objectMapper.readValue(result.response.contentAsString, DiaperChangeRecording::class.java)
        assertThat(diaperChangeRecording.diaperStatus, equalTo(DiaperStatus.Clean))
    }

    @Test
    @Order(2)
    fun `should list diaper changes`() {
        val result = mockMvc.perform(get("/children/${child.id}/diaper"))
            .andExpect(status().isOk)
            .andReturn()

        val jsonResponse = result.response.contentAsString
        val diaperChanges: List<DiaperChangeRecording> = objectMapper.readValue(jsonResponse, object: TypeReference<List<DiaperChangeRecording>>() {})
        assertThat(diaperChanges.size, equalTo(1))
    }

    @Test
    @Order(3)
    fun `should get diaper change`() {
        val result = mockMvc.perform(get("/children/${child.id}/diaper/1"))
            .andExpect(status().isOk)
            .andReturn()

        val diaperChangeRecording = objectMapper.readValue(result.response.contentAsString, DiaperChangeRecording::class.java)
        assertThat(diaperChangeRecording.diaperStatus, equalTo(DiaperStatus.Clean))
    }

    @Test
    @Order(4)
    fun `should edit diaper change successfully`() {
        val diaperChangeDto = DiaperChangeDto(LocalDateTime.now(), DiaperStatus.Pee)
        val json = objectMapper.writeValueAsString(diaperChangeDto)

        val result = mockMvc.perform(put("/children/${child.id}/diaper/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val diaperChangeRecording = objectMapper.readValue(result.response.contentAsString, DiaperChangeRecording::class.java)
        assertThat(diaperChangeRecording.diaperStatus, equalTo(DiaperStatus.Pee))
    }

    @Test
    @Order(5)
    fun `should delete diaper change`() {
        mockMvc.perform(delete("/children/${child.id}/diaper/1"))
            .andExpect(status().isOk)
            .andReturn()
    }
}


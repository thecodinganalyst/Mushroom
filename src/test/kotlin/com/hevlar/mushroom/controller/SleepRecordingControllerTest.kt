package com.hevlar.mushroom.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hevlar.mushroom.controller.dto.ChildDto
import com.hevlar.mushroom.controller.dto.SleepRecordingDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.SleepRecording
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDate
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class SleepRecordingControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var child: Child

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
    }

    @Test
    @Order(1)
    fun `should add sleep recording successfully`() {
        val sleepRecordingDto = SleepRecordingDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1))
        val json = objectMapper.writeValueAsString(sleepRecordingDto)

        val result = mockMvc.perform(post("/children/${child.id}/sleep")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val sleepRecording = objectMapper.readValue(result.response.contentAsString, SleepRecording::class.java)
        assertThat(sleepRecording.dateTime, equalTo(sleepRecordingDto.dateTime))
    }

    @Test
    @Order(2)
    fun `should get sleep recording`() {
        val result = mockMvc.perform(get("/children/${child.id}/sleep/1"))
            .andExpect(status().isOk)
            .andReturn()

        val sleepRecording = objectMapper.readValue(result.response.contentAsString, SleepRecording::class.java)
        assertThat(sleepRecording.id, equalTo(1L))
    }

    @Test
    @Order(3)
    fun `should list sleep recordings`() {
        val result = mockMvc.perform(get("/children/${child.id}/sleep"))
            .andExpect(status().isOk)
            .andReturn()

        val jsonResponse = result.response.contentAsString
        val sleepRecordings: List<SleepRecording> = objectMapper.readValue(jsonResponse, object: TypeReference<List<SleepRecording>>() {})
        assertThat(sleepRecordings.size, equalTo(1))
    }

    @Test
    @Order(4)
    fun `should edit sleep recording successfully`() {
        val sleepRecordingDto = SleepRecordingDto(LocalDateTime.now(), LocalDateTime.now().plusHours(2))
        val json = objectMapper.writeValueAsString(sleepRecordingDto)

        val result = mockMvc.perform(put("/children/${child.id}/sleep/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val sleepRecording = objectMapper.readValue(result.response.contentAsString, SleepRecording::class.java)
        assertThat(sleepRecording.until, equalTo(sleepRecordingDto.until))
    }

    @Test
    @Order(5)
    fun `should delete sleep recording`() {
        mockMvc.perform(delete("/children/${child.id}/sleep/1"))
            .andExpect(status().isOk)
            .andReturn()
    }
}

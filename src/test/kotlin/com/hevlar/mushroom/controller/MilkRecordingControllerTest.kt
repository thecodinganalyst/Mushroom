package com.hevlar.mushroom.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hevlar.mushroom.controller.dto.ChildDto
import com.hevlar.mushroom.controller.dto.MilkRecordingDto
import com.hevlar.mushroom.model.Child
import com.hevlar.mushroom.model.MilkRecording
import com.hevlar.mushroom.model.MilkType
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
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

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class MilkRecordingControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

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
    }

    @Test
    @Order(1)
    fun `should add milk recording successfully`() {
        val milkRecordingDto = MilkRecordingDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), MilkType.BottleBreast, 120)
        val json = objectMapper.writeValueAsString(milkRecordingDto)

        val result = mockMvc.perform(post("/children/${child.id}/milk")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val milkRecording = objectMapper.readValue(result.response.contentAsString, MilkRecording::class.java)
        assertThat(milkRecording.type, equalTo(MilkType.BottleBreast))
    }

    @Test
    @Order(2)
    fun `should list milk recordings`() {
        val result = mockMvc.perform(get("/children/${child.id}/milk"))
            .andExpect(status().isOk)
            .andReturn()

        val jsonResponse = result.response.contentAsString
        val milkRecordings: List<MilkRecording> = objectMapper.readValue(jsonResponse, object: TypeReference<List<MilkRecording>>() {})
        assertThat(milkRecordings.size, equalTo(1))
    }

    @Test
    @Order(3)
    fun `should get milk recording`() {
        val result = mockMvc.perform(get("/children/${child.id}/milk/1"))
            .andExpect(status().isOk)
            .andReturn()

        val milkRecording = objectMapper.readValue(result.response.contentAsString, MilkRecording::class.java)
        assertThat(milkRecording.type, equalTo(MilkType.BottleBreast))
    }

    @Test
    @Order(4)
    fun `should edit milk recording successfully`() {
        val milkRecordingDto = MilkRecordingDto(LocalDateTime.now(), LocalDateTime.now().plusHours(1), MilkType.BreastLeft, null)
        val json = objectMapper.writeValueAsString(milkRecordingDto)

        val result = mockMvc.perform(put("/children/${child.id}/milk/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val milkRecording = objectMapper.readValue(result.response.contentAsString, MilkRecording::class.java)
        assertThat(milkRecording.type, equalTo(MilkType.BreastLeft))
    }

    @Test
    @Order(5)
    fun `should delete milk recording`() {
        val result = mockMvc.perform(delete("/children/${child.id}/milk/1"))
            .andExpect(status().isOk)
            .andReturn()
    }
}

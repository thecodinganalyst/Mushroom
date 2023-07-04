package com.hevlar.mushroom.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.hevlar.mushroom.controller.dto.ChildDto
import com.hevlar.mushroom.model.Child
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

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ChildControllerTest() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    private lateinit var child: Child

    @Test
    @Order(1)
    fun `should add child successfully`() {
        val childDto = ChildDto("John", LocalDate.of(2020,1, 1))
        val json = objectMapper.writeValueAsString(childDto)

        val result = mockMvc.perform(post("/children")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isCreated)
            .andReturn()

        child = objectMapper.readValue(result.response.contentAsString, Child::class.java)
        assertThat(child.name, equalTo("John"))
        assertThat(child.dob, equalTo(LocalDate.of(2020, 1, 1)))
    }

    @Test
    @Order(2)
    fun `should list child`() {
        val result = mockMvc.perform(get("/children"))
            .andExpect(status().isOk)
            .andReturn()
        val jsonResponse = result.response.contentAsString
        val children: List<Child> = objectMapper.readValue(jsonResponse, object: TypeReference<List<Child>>() {})
        assertThat(children.size, equalTo(1))
    }

    @Test
    @Order(3)
    fun `should get child`() {
        val result = mockMvc.perform(get("/children/${child.id}"))
            .andExpect(status().isOk)
            .andReturn()

        val child = objectMapper.readValue(result.response.contentAsString, Child::class.java)
        assertThat(child.name, equalTo("John"))
        assertThat(child.dob, equalTo(LocalDate.of(2020, 1, 1)))
    }

    @Test
    @Order(4)
    fun `should edit child successfully`() {
        val childDto = ChildDto("John Hancock", LocalDate.of(2020,2, 1))
        val json = objectMapper.writeValueAsString(childDto)

        val result = mockMvc.perform(put("/children/${child.id}")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json))
            .andExpect(status().isOk)
            .andReturn()

        val child = objectMapper.readValue(result.response.contentAsString, Child::class.java)
        assertThat(child.name, equalTo("John Hancock"))
        assertThat(child.dob, equalTo(LocalDate.of(2020, 2, 1)))
    }

    @Test
    @Order(5)
    fun `should delete child`() {
        mockMvc.perform(delete("/children/${child.id}"))
            .andExpect(status().isOk)
            .andReturn()
    }
}

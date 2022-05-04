package com.hyunwoo.kopring.controller

import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@WebMvcTest
class UserControllerTest(
    private val mvc: MockMvc
) {

    @Test
    fun `user를 실행하는 경우`() {
        mvc.get("/user")
            .andExpect {
                content { string("유저가 없습니다!") }
            }
    }

    @Test
    fun getUserByName() {
        mvc.get("/user/hyunwoo")
            .andExpect {
                content { string("hyunwoo을 가진 유저가 없습니다") }
            }
    }

    @Test
    fun getUserByInfo() {
        mvc.get("/user") {
            param("name", "hyunwoo")
            param("part", "server")
        }.andExpect {
            content { string("조회 실패") }
        }
    }

    @Test
    fun postUser() {

    }

    @Test
    fun putUser() {
    }

    @Test
    fun deleteUser() {
    }
}

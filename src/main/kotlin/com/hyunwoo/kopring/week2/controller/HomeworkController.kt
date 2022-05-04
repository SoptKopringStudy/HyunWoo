package com.hyunwoo.kopring.week2.controller

import com.hyunwoo.kopring.model.BaseResponse
import com.hyunwoo.kopring.model.User
import com.hyunwoo.kopring.model.UserDto
import com.hyunwoo.kopring.week2.service.HomeworkService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/homework")
class HomeworkController(
    private val homeworkService: HomeworkService
) {
    @GetMapping("")
    fun getUser() = homeworkService.getAllUser()

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable("name") name: String): BaseResponse<String> {
        homeworkService.getUserByName(name)
            .onSuccess { return BaseResponse.success(it) }
            .onFailure { return BaseResponse.failure(it.message ?: "해당 유저가 없습니다") }
        throw RuntimeException("Unreachable Code")
    }

    @GetMapping("/info")
    fun getUserByQuery(
        @RequestParam("part") part: String,
        @RequestParam("name") name: String,
    ): BaseResponse<String> {
        homeworkService.getUserByInfo(name, part)
            .onSuccess { return BaseResponse.success(it) }
            .onFailure { return BaseResponse.failure(it.message ?: "해당 유저가 없습니다") }
        throw RuntimeException("Unreachable Code")
    }

    @PostMapping("")
    fun postUser(@RequestBody user: UserDto) = homeworkService.registerUser(user)

    @PutMapping("")
    fun putUser(@RequestBody user: User): BaseResponse<String> {
        homeworkService.patchUser(user)
            .onSuccess { return BaseResponse.success(it) }
            .onFailure { return BaseResponse.failure(it.message ?: "수정 실패") }
        throw RuntimeException("Unreachable Code")
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable("id") id: Int): String {
        homeworkService.deleteUser(id)
            .onSuccess { return it }
            .onFailure { return it.message ?: "삭제 실패" }
        throw RuntimeException("Unreachable Code")
    }
}

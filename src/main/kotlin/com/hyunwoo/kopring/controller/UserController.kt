package com.hyunwoo.kopring.controller

import com.hyunwoo.kopring.model.UserDto
import com.hyunwoo.kopring.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("user")
class UserController(
    private val userService: UserService
) {
    @GetMapping("")
    fun getUsers() = userService.findAllUsers()

    @GetMapping("/name/{name}")
    fun getUserByName(@PathVariable("name") name: String) =
        when (val user = userService.findUserByName(name)) {
            null -> "${name}을 가진 유저가 없습니다"
            else -> "이름: ${user.name} 파트: ${user.part}"
        }

    @GetMapping("/info")
    fun getUserByInfo(
        @RequestParam("name") name: String,
        @RequestParam("part") part: String
    ) = when (userService.findUserByInfo(name, part)) {
        null -> "조회 실패"
        else -> "조회 성공"
    }

    @PostMapping("")
    fun postUser(@RequestBody user: UserDto): String {
        userService.registerUser(user)
            .onSuccess { return it }
            .onFailure { return it.message ?: "${user.name} 등록 실패" }
        throw RuntimeException("Unreachable Code")
    }

    @PutMapping("")
    fun putUser(@RequestBody user: UserDto) = userService.patchUser(user)


    @DeleteMapping("/{name}")
    fun deleteUser(@PathVariable("name") name: String) =
        if (userService.deleteUser(name)) "$name 삭제 완료" else "해당 유저가 없습니다"
}

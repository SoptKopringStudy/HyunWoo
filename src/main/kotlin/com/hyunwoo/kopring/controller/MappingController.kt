package com.hyunwoo.kopring.controller

import com.hyunwoo.kopring.model.UserDto
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("post")
class MappingController {
    @PostMapping("")
    fun postUser(@RequestBody user: UserDto) = user.name

    @PutMapping("")
    fun putUser(@RequestBody user: UserDto) = user.part

    @DeleteMapping("")
    fun deleteUser(@RequestBody user: UserDto) = "Delete Success"
}

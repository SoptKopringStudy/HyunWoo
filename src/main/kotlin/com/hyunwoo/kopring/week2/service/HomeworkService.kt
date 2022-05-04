package com.hyunwoo.kopring.week2.service

import com.hyunwoo.kopring.model.User
import com.hyunwoo.kopring.model.UserDto

interface HomeworkService {
    fun getAllUser(): String
    fun getUserByName(name: String): Result<String>
    fun getUserByInfo(name: String, part: String): Result<String>
    fun registerUser(userDto: UserDto)
    fun patchUser(user: User): Result<String>
    fun deleteUser(id: Int): Result<String>
}

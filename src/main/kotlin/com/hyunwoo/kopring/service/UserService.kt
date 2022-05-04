package com.hyunwoo.kopring.service

import com.hyunwoo.kopring.model.User
import com.hyunwoo.kopring.model.UserDto

interface UserService {
    fun findAllUsers(): String
    fun findUserByName(name: String): User?
    fun findUserByInfo(name: String, part: String): User?
    fun registerUser(user: UserDto): Result<String>
    fun patchUser(user: UserDto): String
    fun deleteUser(name: String): Boolean
}

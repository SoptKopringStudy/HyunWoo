package com.hyunwoo.kopring.service

import com.hyunwoo.kopring.model.User
import com.hyunwoo.kopring.model.UserDto
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    private val userList = mutableListOf<User>()

    override fun findAllUsers() = if (userList.isEmpty()) "유저가 없습니다!" else userList.joinToString { it.name }

    override fun findUserByName(name: String) = userList.find { it.name == name }

    override fun findUserByInfo(name: String, part: String) = userList.find { it.name == name && it.part == part }

    override fun registerUser(user: UserDto): Result<String> {
        return when (findUserByInfo(user.name, user.part)) {
            null -> {
                userList.add(user.toUser(userList.size))
                Result.success("${user.name} 등록 성공")
            }
            else -> Result.failure(IllegalStateException("이미 존재하는 유저입니다. 이름: ${user.name}"))
        }
    }

    override fun patchUser(user: UserDto): String {
        return when (findUserByInfo(user.name, user.part)) {
            null -> {
                userList.add(user.toUser(userList.size))
                "${user.name} 등록 성공"
            }
            else -> {
                val userIndex = userList.indexOfFirst { it.name == user.name || it.part == user.part }
                userList[userIndex] = user.toUser(userList[userIndex].id)
                "${user.name} 수정 성공"
            }
        }
    }

    override fun deleteUser(name: String): Boolean {
        return when (userList.find { it.name == name }) {
            null -> false
            else -> {
                userList.removeIf { it.name == name }
                true
            }
        }
    }
}

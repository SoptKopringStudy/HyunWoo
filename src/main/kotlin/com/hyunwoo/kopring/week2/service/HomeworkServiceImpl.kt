package com.hyunwoo.kopring.week2.service

import com.hyunwoo.kopring.model.User
import com.hyunwoo.kopring.model.UserDto
import org.springframework.stereotype.Service

@Service
class HomeworkServiceImpl : HomeworkService {
    private val userList: MutableList<User> = mutableListOf()

    override fun getAllUser() = if (userList.isEmpty()) "유저가 없습니다!" else userList.joinToString { it.name }

    override fun getUserByName(name: String): Result<String> {
        return when (val user = userList.find { it.isNameSame(name) }) {
            null -> Result.failure(IllegalStateException("${name}을 가진 유저가 없습니다!"))
            else -> Result.success(user.introduce())
        }
    }

    override fun getUserByInfo(name: String, part: String): Result<String> {
        return when (userList.find { it.isUserEqual(name, part) }) {
            null -> Result.failure(IllegalStateException("조회 실패"))
            else -> Result.success("조회 성공")
        }
    }

    override fun registerUser(userDto: UserDto) {
        userList.add(userDto.toUser(userList.size))
    }

    override fun patchUser(user: User): Result<String> {
        return when (val user = userList.find { it.isIdEqual(user.id) }) {
            null -> Result.failure(IllegalStateException("수정 실패"))
            else -> {
                val patchIndex = userList.indexOfFirst { it.isIdEqual(user.id) }
                userList[patchIndex] = user
                Result.success("수정 성공")
            }
        }
    }

    override fun deleteUser(id: Int): Result<String> {
        return when (userList.find { it.isIdEqual(id.toLong()) }) {
            null -> Result.failure(IllegalStateException("삭제 실패"))
            else -> {
                userList.removeIf { it.isIdEqual(id.toLong()) }
                Result.success("삭제 성공")
            }
        }
    }
}

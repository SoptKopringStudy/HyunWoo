package com.hyunwoo.kopring.model

data class UserDto(
    val name: String,
    val part: String
) {
    fun toUser(id: Int) = User(
        id = id.toLong(),
        name = name,
        part = part
    )

    fun toUser(id: Long) = User(
        id = id,
        name = name,
        part = part
    )
}

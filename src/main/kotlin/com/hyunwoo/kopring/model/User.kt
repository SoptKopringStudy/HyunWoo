package com.hyunwoo.kopring.model

data class User(
    val id: Long,
    val name: String,
    val part: String
) {
    fun introduce() = "이름: $name 파트: $part"
    fun isNameSame(other: String) = name == other
    fun isUserEqual(otherName: String, otherPart: String) = otherName == name && otherPart == part
    fun isIdEqual(id: Long) = id == this.id
}

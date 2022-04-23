package com.hyunwoo.kopring.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("")
    fun default() = "DEFAULT!"

    @GetMapping("hello")
    fun hello() = "HELLO!"

    @GetMapping("/name/{name}")
    fun getName(@PathVariable("name") name: String) = name

    @GetMapping("/info")
    fun getPartIntro(
        @RequestParam("part", defaultValue = "") part: String,
        @RequestParam("type") type: String
    ) = "파트: $part 타입: $type"
}

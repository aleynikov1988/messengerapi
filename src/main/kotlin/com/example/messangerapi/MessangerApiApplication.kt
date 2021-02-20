package com.example.messangerapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MessangerApiApplication

fun main(args: Array<String>) {
    runApplication<MessangerApiApplication>(*args)
}

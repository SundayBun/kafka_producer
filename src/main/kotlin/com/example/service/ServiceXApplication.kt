package com.example.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ServiceXApplication {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            runApplication<ServiceXApplication>(*args)
        }
    }
}
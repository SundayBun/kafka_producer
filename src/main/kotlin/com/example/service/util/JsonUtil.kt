package com.example.service.util

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

fun <T> getString(obj: T): String {
    val mapper = jacksonObjectMapper()
    return mapper.writeValueAsString(obj)
}

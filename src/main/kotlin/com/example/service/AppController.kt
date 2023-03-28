package com.example.service

import com.example.service.broker.KafkaProducer
import com.example.service.model.RespModel
import com.example.service.util.getString
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api")
class AppController(private val kafkaProducer: KafkaProducer, @Value("\${topic.producer}") private val topic: String) {

    @PostMapping("/sendMessage")
    fun sendMessage(@RequestBody body: RespModel): ResponseEntity<String>? {
        kafkaProducer.producerPublisher(body, topic).subscribe()
        return ResponseEntity(HttpStatus.OK)
    }
}
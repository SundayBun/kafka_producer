package com.example.service.config

import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.sender.SenderOptions

@Configuration
class KafkaProducerConfig {

    @Bean
    fun reactiveKafkaProducerTemplate(properties: KafkaProperties): ReactiveKafkaProducerTemplate<String, String> {
        val producerProperty = properties.buildProducerProperties()
        val senderOptions = SenderOptions.create<String, String>(producerProperty)
        senderOptions.maxInFlight(1024)
        return ReactiveKafkaProducerTemplate(senderOptions)
    }
}
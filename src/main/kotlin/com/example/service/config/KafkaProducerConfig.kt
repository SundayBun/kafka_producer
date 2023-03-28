package com.example.service.config

import com.example.service.model.RespModel
import org.springframework.boot.autoconfigure.kafka.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import reactor.kafka.sender.SenderOptions

@Configuration
class KafkaProducerConfig {

    @Bean
    fun reactiveKafkaProducerTemplate(properties: KafkaProperties): ReactiveKafkaProducerTemplate<String, RespModel> {
        val producerProperty = properties.buildProducerProperties()
        val senderOptions = SenderOptions.create<String, RespModel>(producerProperty)
        senderOptions.maxInFlight(1024)
        return ReactiveKafkaProducerTemplate(senderOptions)
    }
}
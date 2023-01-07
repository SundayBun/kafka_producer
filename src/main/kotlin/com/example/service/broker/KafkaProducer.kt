package com.example.service.broker

import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kafka.sender.SenderResult

@Slf4j
@Component
class KafkaProducer @Autowired constructor(
    private val reactiveKafkaProducerTemplate: ReactiveKafkaProducerTemplate<String, String>
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    fun producerPublisher(message: String, topic: String): Mono<SenderResult<Void>> {
        logger.info("send to topic={}, {}", topic, message);

        return reactiveKafkaProducerTemplate.send(topic, message)
            .doOnSuccess { senderResult ->
                logger.info(
                    "sent {} offset : {}",
                    message,
                    senderResult.recordMetadata().offset()
                )
            }
            .doOnError { throwable ->
                logger.error(
                    "Error while sending message : {}", throwable.message
                )
            }
    }
}
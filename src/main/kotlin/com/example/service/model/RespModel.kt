package com.example.service.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class RespModel(
    @JsonProperty("xml")
    var xmlResp: String,
    @JsonProperty("id")
    var messageId: String,
    @JsonProperty("type")
    var messType: String,
    @JsonProperty("timestamp")
    var sendingTimestamp: Date
)

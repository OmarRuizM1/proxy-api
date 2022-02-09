package com.proxy.api.proxyapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

data class AppDto(
    @JsonProperty("app")
    @SerializedName("app")
    val app: String
)
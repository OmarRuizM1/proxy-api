package com.proxy.api.proxyapi.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.google.gson.annotations.SerializedName

data class URLDto(
    @JsonProperty("url")
    @SerializedName("url")
    val url: String
)
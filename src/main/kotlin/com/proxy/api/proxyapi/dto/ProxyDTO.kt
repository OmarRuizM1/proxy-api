package com.proxy.api.proxyapi.dto

import com.google.gson.annotations.SerializedName

data class ProxyDTO(
    @SerializedName("protocol")
    val protocol: String,
    @SerializedName("host")
    val host: String,
    @SerializedName("port")
    val port: Int
)

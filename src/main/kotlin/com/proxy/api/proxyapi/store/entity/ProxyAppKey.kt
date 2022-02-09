package com.proxy.api.proxyapi.store.entity

import java.io.Serializable

data class ProxyAppKey(
    val app: String? = null,
    val url: String? = null
) : Serializable

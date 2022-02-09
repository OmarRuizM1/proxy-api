package com.proxy.api.proxyapi.parse

import com.proxy.api.proxyapi.store.entity.Proxy
import com.proxy.api.proxyapi.store.entity.ProxyApp
import org.springframework.stereotype.Component

@Component
class ProxyParser {

    fun fromURLToProxyAppEntity(app: String, url: String): ProxyApp {
        val proxy = fromURLToProxyEntity(url)
        val proxyApp = ProxyApp()
        proxyApp.app = app
        proxyApp.url = proxy.url
        proxyApp.protocol = proxy.protocol
        proxyApp.host = proxy.host
        proxyApp.port = proxy.port
        proxyApp.used = false
        return proxyApp
    }

    fun fromURLToProxyEntity(url: String): Proxy {
        val newUrl = if (url.endsWith("/")) url.substring(0, url.length - 1) else url

        val split = newUrl.split("://")
        val protocol = split[0].toLowerCase()
        val split2 = split[1].split(":")
        val host = split2[0]
        val port = split2[1]

        val proxy = Proxy()
        proxy.url = newUrl
        proxy.protocol = protocol
        proxy.host = host
        proxy.port = port.toInt()
        proxy.used = false
        return proxy
    }
}
package com.proxy.api.proxyapi.mappers

import com.proxy.api.proxyapi.dto.ProxyAppDTO
import com.proxy.api.proxyapi.store.entity.ProxyApp
import org.springframework.stereotype.Component

@Component
class ProxyAppMapper {

    fun toProxy(p: ProxyAppDTO): ProxyApp {
        val url = p.protocol + "://" + p.host + ":" + p.port
        val proxy = ProxyApp()
        proxy.app = p.app
        proxy.url = url
        proxy.protocol = p.protocol
        proxy.host = p.host
        proxy.port = p.port
        proxy.used = p.used
        return proxy
    }

    fun toProxies(proxies: List<ProxyAppDTO>): List<ProxyApp> {
        return proxies.map { toProxy(it) }.toList()
    }
}
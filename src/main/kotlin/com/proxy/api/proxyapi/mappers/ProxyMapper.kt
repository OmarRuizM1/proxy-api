package com.proxy.api.proxyapi.mappers

import com.proxy.api.proxyapi.dto.ProxyDTO
import com.proxy.api.proxyapi.store.entity.Proxy
import org.springframework.stereotype.Component

@Component
class ProxyMapper {

    fun toProxy(p: ProxyDTO): Proxy {
        val url = p.protocol + "://" + p.host + ":" + p.port
        val proxy = Proxy()
        proxy.url = url
        proxy.protocol = p.protocol
        proxy.host = p.host
        proxy.port = p.port
        proxy.used = false
        return proxy
    }

    fun toProxies(proxies: List<ProxyDTO>): List<Proxy> {
        return proxies.map { toProxy(it) }.toList()
    }
}
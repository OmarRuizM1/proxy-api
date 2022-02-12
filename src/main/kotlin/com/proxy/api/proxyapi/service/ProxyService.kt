package com.proxy.api.proxyapi.service

import com.proxy.api.proxyapi.parse.ProxyParser
import com.proxy.api.proxyapi.store.entity.Proxy
import com.proxy.api.proxyapi.store.repository.ProxyRepository
import org.springframework.stereotype.Service

@Service
class ProxyService(private val proxyRepository: ProxyRepository, private val proxyParser: ProxyParser) {

    fun save(proxy: Proxy) {
        proxyRepository.save(proxy)
    }

    fun saveAll(proxy: List<Proxy>) {
        proxy.forEach {
            if (it.url != null && !proxyRepository.existsById(it.url!!)) {
                save(it)
            }
        }
//        proxyRepository.saveAll(proxy)
    }

    fun findAnyNotUsed(): Proxy? {
        return proxyRepository.findAnyByUsedIsFalse()
    }

    fun findAllNotUsed(): List<Proxy?>? {
        return proxyRepository.findAllByUsedIsFalse()
    }

    fun findAllNotUsedWithPort8080(): List<Proxy?>? {
        return proxyRepository.findAllByUsedIsFalse()?.filter { it?.port == 8080 }
    }

    fun findAllNotUsedWithPortHTTP(): List<Proxy?>? {
        return proxyRepository.findAllByUsedIsFalse()?.filter { it?.protocol == "HTTP" || it?.protocol == "http" }
    }

    fun findFirstNotUsedWithPortHTTP(): Proxy? {
        return proxyRepository.findFirstByUsedAndProtocol(false, "HTTP")
    }

    fun saveByUrl(url: String) {
        proxyRepository.save(proxyParser.fromURLToProxyEntity(url))
    }

    fun bulkUpdateUsedFalse() {
        proxyRepository.findAllByUsedIsTrue()?.forEach {
            if (it != null) {
                it.used = false
                save(it)
            }
        }
    }

    fun updateUsedProxy(url: String) {
        val optionalUrl = proxyRepository.findById(url)
        if (optionalUrl.isPresent) {
            val proxy = optionalUrl.get()
            proxy.used = true
            save(proxy)
        }
    }

}
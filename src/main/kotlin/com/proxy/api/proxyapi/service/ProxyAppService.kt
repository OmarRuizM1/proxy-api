package com.proxy.api.proxyapi.service

import com.proxy.api.proxyapi.parse.ProxyParser
import com.proxy.api.proxyapi.store.entity.ProxyApp
import com.proxy.api.proxyapi.store.entity.ProxyAppKey
import com.proxy.api.proxyapi.store.repository.ProxyAppRepository
import org.springframework.stereotype.Service

@Service
class ProxyAppService(private val proxyAppRepository: ProxyAppRepository, private val proxyParser: ProxyParser) {

    fun save(proxyApp: ProxyApp) {
        proxyAppRepository.save(proxyApp)
    }

    fun saveAll(proxyApp: List<ProxyApp>) {
        proxyApp.forEach {
            if (it.app != null && it.url != null && !proxyAppRepository.existsById(ProxyAppKey(it.app, it.url))) {
                save(it)
            }
        }
    }

    fun findAnyNotUsed(app: String): ProxyApp? {
        return proxyAppRepository.findAnyByUsedIsFalseAndApp(app)
    }

    fun findAllNotUsed(app: String): List<ProxyApp?>? {
        return proxyAppRepository.findAllByUsedIsFalseAndApp(app)
    }

    fun findAllNotUsedWithPort8080(app: String): List<ProxyApp?>? {
        return proxyAppRepository.findAllByUsedIsFalseAndApp(app)?.filter { it?.port == 8080 }
    }

    fun saveByUrl(app: String, url: String) {
        proxyAppRepository.save(proxyParser.fromURLToProxyAppEntity(app, url))
    }

    fun bulkUpdateUsedFalse(app: String) {
        proxyAppRepository.findAllByUsedIsTrueAndApp(app)?.forEach {
            if (it != null) {
                it.used = false
                save(it)
            }
        }
    }

    fun updateUsedProxy(app: String, url: String) {
        val optionalUrl = proxyAppRepository.findById(ProxyAppKey(app, url))
        if (optionalUrl.isPresent) {
            val proxy = optionalUrl.get()
            proxy.used = true
            save(proxy)
        }
    }

}
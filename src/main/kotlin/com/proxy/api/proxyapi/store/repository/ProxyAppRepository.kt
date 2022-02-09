package com.proxy.api.proxyapi.store.repository

import com.proxy.api.proxyapi.store.entity.ProxyApp
import com.proxy.api.proxyapi.store.entity.ProxyAppKey
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProxyAppRepository : CrudRepository<ProxyApp, ProxyAppKey> {
    fun findAllByUsedIsFalseAndApp(app: String): List<ProxyApp?>?
    fun findAllByUsedIsTrueAndApp(app: String): List<ProxyApp?>?
    fun findAnyByUsedIsFalseAndApp(app: String): ProxyApp?
}
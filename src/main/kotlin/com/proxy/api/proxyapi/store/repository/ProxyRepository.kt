package com.proxy.api.proxyapi.store.repository

import com.proxy.api.proxyapi.store.entity.Proxy
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProxyRepository : CrudRepository<Proxy, String> {

    fun findAllByUsedIsFalse(): List<Proxy?>?

    fun findAllByUsedIsTrue(): List<Proxy?>?

    fun findFirstByUsedAndProtocol(used: Boolean, protocol: String): Proxy?

    fun findAnyByUsedIsFalse(): Proxy?
}
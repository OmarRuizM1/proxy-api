package com.proxy.api.proxyapi.store.entity

import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity
@IdClass(ProxyAppKey::class)
class ProxyApp {

    @Id
    var app: String? = null

    @Id
    var url: String? = null
    var protocol: String? = null
    var host: String? = null
    var port: Int? = null
    var used: Boolean = false
}
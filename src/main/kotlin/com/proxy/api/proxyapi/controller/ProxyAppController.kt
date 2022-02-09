package com.proxy.api.proxyapi.controller

import com.proxy.api.proxyapi.dto.AppAndUrlRequest
import com.proxy.api.proxyapi.dto.AppDto
import com.proxy.api.proxyapi.dto.ProxyAppDTO
import com.proxy.api.proxyapi.mappers.ProxyAppMapper
import com.proxy.api.proxyapi.service.ProxyAppService
import com.proxy.api.proxyapi.store.entity.ProxyApp
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/proxyApp/api")
class ProxyAppController(private val proxyAppService: ProxyAppService, private val proxyAppMapper: ProxyAppMapper) {

    @PostMapping("/saveByUrl")
    fun saveByUrl(@RequestBody appAndUrlRequest: AppAndUrlRequest) = proxyAppService.saveByUrl(appAndUrlRequest.app, appAndUrlRequest.url)

    @PostMapping("/save")
    fun save(@RequestBody proxyAppDTO: ProxyAppDTO) = proxyAppService.save(proxyAppMapper.toProxy(proxyAppDTO))

    @PostMapping("/saveAll")
    fun saveAll(@RequestBody proxyAppDTOs: List<ProxyAppDTO>) = proxyAppService.saveAll(proxyAppMapper.toProxies(proxyAppDTOs))

    @PostMapping("/findNotUsedProxy")
    fun findNotUsedProxy(@RequestBody appDto: AppDto): ProxyApp? = proxyAppService.findAnyNotUsed(appDto.app)

    @PostMapping("/findAllNotUsed")
    fun findAllNotUsed(@RequestBody appDto: AppDto): List<ProxyApp?>? = proxyAppService.findAllNotUsed(appDto.app)

    @PostMapping("/findAllNotUsedWithPort8080")
    fun findAllNotUsedWithPort8080(@RequestBody appDto: AppDto): List<ProxyApp?>? = proxyAppService.findAllNotUsedWithPort8080(appDto.app)

    @PostMapping("/updateUsedProxy")
    fun updateUsedProxy(@RequestBody appAndUrlRequest: AppAndUrlRequest) = proxyAppService.updateUsedProxy(appAndUrlRequest.app, appAndUrlRequest.url)

    @PostMapping("/bulkUpdateUsedFalse")
    fun bulkUpdateUsedFalse(@RequestBody appDto: AppDto) = proxyAppService.bulkUpdateUsedFalse(appDto.app)


}
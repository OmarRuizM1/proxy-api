package com.proxy.api.proxyapi.controller

import com.proxy.api.proxyapi.dto.ProxyDTO
import com.proxy.api.proxyapi.dto.URLDto
import com.proxy.api.proxyapi.mappers.ProxyMapper
import com.proxy.api.proxyapi.service.ProxyService
import com.proxy.api.proxyapi.store.entity.Proxy
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/proxy/api")
class ProxyController(private val proxyService: ProxyService, private val proxyMapper: ProxyMapper) {

    @PostMapping("/saveByUrl")
    fun saveByUrl(@RequestBody urlDto: URLDto) = proxyService.saveByUrl(urlDto.url)

    @PostMapping("/save")
    fun save(@RequestBody proxyDTO: ProxyDTO) = proxyService.save(proxyMapper.toProxy(proxyDTO))

    @PostMapping("/saveAll")
    fun saveAll(@RequestBody proxyDTOs: List<ProxyDTO>) = proxyService.saveAll(proxyMapper.toProxies(proxyDTOs))

    @GetMapping("/findNotUsedProxy")
    fun findNotUsedProxy(): Proxy? = proxyService.findAnyNotUsed()

    @GetMapping("/findAllNotUsed")
    fun findAllNotUsed(): List<Proxy?>? = proxyService.findAllNotUsed()

    @GetMapping("/findAllNotUsedWithPort8080")
    fun findAllNotUsedWithPort8080(): List<Proxy?>? = proxyService.findAllNotUsedWithPort8080()

    @PostMapping("/updateUsedProxy")
    fun updateUsedProxy(@RequestBody urlDto: URLDto) = proxyService.updateUsedProxy(urlDto.url)

    @GetMapping("/bulkUpdateUsedFalse")
    fun bulkUpdateUsedFalse() = proxyService.bulkUpdateUsedFalse()


}
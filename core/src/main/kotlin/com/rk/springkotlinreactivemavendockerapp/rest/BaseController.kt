package com.rk.springkotlinreactivemavendockerapp.rest

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["api/v1"], produces = [MediaType.APPLICATION_JSON_VALUE])
class BaseController(
    val baseService: BaseService
){

    @GetMapping(value = ["ping"])
    fun ping(): String {
        return baseService.handlePing()
    }
}
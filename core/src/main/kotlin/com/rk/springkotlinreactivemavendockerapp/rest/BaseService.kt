package com.rk.springkotlinreactivemavendockerapp.rest

import org.springframework.stereotype.Service

@Service
class BaseService {

    fun handlePing(): String {
        return "Hello from BaseService"
    }
}
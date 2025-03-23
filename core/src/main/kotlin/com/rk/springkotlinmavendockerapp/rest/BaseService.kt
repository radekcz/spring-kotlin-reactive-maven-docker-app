package com.rk.springkotlinmavendockerapp.rest

import org.springframework.stereotype.Service

@Service
class BaseService {

    fun handlePing(): String {
        return "Hello from BaseService"
    }
}
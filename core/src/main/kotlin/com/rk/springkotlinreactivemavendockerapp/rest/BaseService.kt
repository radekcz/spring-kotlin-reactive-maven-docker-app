package com.rk.springkotlinreactivemavendockerapp.rest

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BaseService {

    fun handlePing(): Mono<String> {
        return Mono.just("Hello from BaseService")
    }
}
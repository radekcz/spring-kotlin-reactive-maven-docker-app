package com.rk.springkotlinreactivemavendockerapp.configuration

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@EnableWebFluxSecurity
class TestSecurityConfiguration {

    @Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        return serverHttpSecurity
                .authorizeExchange { exchanges -> exchanges
                            .anyExchange().permitAll() }
                .cors { cors -> cors.disable() }
                .csrf { csrf -> csrf.disable() }
                .build()
    }
}

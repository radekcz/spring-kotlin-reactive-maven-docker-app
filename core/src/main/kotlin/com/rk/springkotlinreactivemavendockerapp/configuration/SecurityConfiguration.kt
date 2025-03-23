package com.rk.springkotlinreactivemavendockerapp.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.SecurityWebFiltersOrder
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.authentication.AuthenticationWebFilter
import reactor.core.publisher.Mono

@EnableWebFluxSecurity
@Configuration
class SecurityConfiguration {

    @Value("\${app.authentication.token}")
    lateinit var authToken: String

    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                it.pathMatchers("/actuator/**").permitAll()
                it.pathMatchers("/api/**").authenticated()
                it.anyExchange().denyAll()
            }
            .csrf { it.disable() }
            .cors { it.disable() }
            .authenticationManager { auth -> Mono.just(auth) }
            .addFilterAt(authTokenAuthFilter(), SecurityWebFiltersOrder.AUTHENTICATION)
            .build()
    }

    @Bean
    fun authTokenAuthFilter(): AuthenticationWebFilter {
        val authenticationManager = ReactiveAuthenticationManager { auth -> Mono.just(auth) }
        val authenticationFilter = AuthenticationWebFilter(authenticationManager)
        authenticationFilter.setServerAuthenticationConverter(AuthTokenConverter(authToken))
        return authenticationFilter
    }

    companion object {
        const val HEADER_X_AUTH_TOKEN: String = "X-AUTH-TOKEN"
    }
}

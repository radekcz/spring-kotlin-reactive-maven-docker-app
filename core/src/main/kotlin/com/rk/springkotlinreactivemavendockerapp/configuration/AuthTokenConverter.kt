package com.rk.springkotlinreactivemavendockerapp.configuration

import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

class AuthTokenConverter(
    private val authToken: String?
) : ServerAuthenticationConverter {

    override fun convert(exchange: ServerWebExchange): Mono<Authentication> {
        val token = exchange
            .request
            .headers
            .getFirst(SecurityConfiguration.HEADER_X_AUTH_TOKEN)

        return if (isValidToken(token)) {
            Mono.just(PreAuthenticatedAuthenticationToken(SecurityConfiguration.HEADER_X_AUTH_TOKEN, token, emptyList()))
        } else {
            Mono.empty()
        }
    }

    private fun isValidToken(token: String?) = token == authToken

}
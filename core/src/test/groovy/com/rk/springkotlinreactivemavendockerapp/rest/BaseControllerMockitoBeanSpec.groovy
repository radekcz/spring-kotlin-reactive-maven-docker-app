package com.rk.springkotlinreactivemavendockerapp.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Specification

import static org.mockito.Mockito.when

@WebFluxTest(controllers = BaseController)
@AutoConfigureWebTestClient
class BaseControllerMockitoBeanSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    @MockitoBean
    private BaseService baseService

    def "ping endpoint should return the result"() {
        given:
        when(baseService.handlePing())
                .thenReturn(Mono.just("Test message from BaseService"))

        when:
        def result = webTestClient
                .get()
                .uri { uriBuilder -> uriBuilder
                        .path("/api/v1/ping")
                        .build()
                }
                .exchange()

        then:
        result.expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("Test message from BaseService")
    }
}
package com.rk.springkotlinreactivemavendockerapp.rest

import com.rk.springkotlinreactivemavendockerapp.configuration.TestSecurityConfiguration
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Specification

@WebFluxTest(controllers = BaseController)
@AutoConfigureWebTestClient
@Import([TestSecurityConfiguration])
class BaseControllerSpringBeanSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    @SpringBean
    private BaseService baseService = Mock(BaseService)

    def "ping endpoint should return the result"() {
        given:
        baseService.handlePing() >> Mono.just("Testing message from BaseService")

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
                .isEqualTo("Testing message from BaseService")
    }
}
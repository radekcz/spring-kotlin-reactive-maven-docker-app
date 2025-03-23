package com.rk.springkotlinreactivemavendockerapp.configuration

import com.rk.springkotlinreactivemavendockerapp.rest.BaseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient
import reactor.core.publisher.Mono
import spock.lang.Specification

import static org.mockito.Mockito.when

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
class SecurityConfigurationSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    @MockitoBean
    private BaseService baseService

    def "should restrict access to secured endpoint as unauthorized"() {
        when:
        def result = webTestClient
                .get()
                .uri { uriBuilder -> uriBuilder
                        .path("/api/v1/ping")
                        .queryParam("instanceName", "pim-secured-instance")
                        .build()
                }
                .exchange()

        then:
        result.expectStatus().isUnauthorized()
    }

    def "should allow access to secured endpoint with auth-token"() {
        given:
        when(baseService.handlePing())
                .thenReturn(Mono.just("New message from BaseService"))

        when:
        def result = webTestClient
                .get()
                .uri { uriBuilder -> uriBuilder
                        .path("/api/v1/ping")
                        .build()
                }
                .header(SecurityConfiguration.HEADER_X_AUTH_TOKEN, 'testing-auth')
                .exchange()

        then:
        result.expectStatus()
                .isOk()
                .expectBody(String.class)
                .isEqualTo("New message from BaseService")
    }

    def "should allow access to unsecured endpoint"() {
        when:
        def result = webTestClient
                .get()
                .uri { uriBuilder -> uriBuilder
                        .path("/actuator/health")
                        .build()
                }
                .exchange()

        then:
        result.expectStatus().isOk()
    }
}

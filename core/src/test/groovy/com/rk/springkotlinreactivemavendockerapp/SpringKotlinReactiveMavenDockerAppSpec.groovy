package com.rk.springkotlinreactivemavendockerapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import spock.lang.Specification

@SpringBootTest
@AutoConfigureMockMvc
class SpringKotlinReactiveMavenDockerAppSpec extends Specification {

    @Autowired
    private WebTestClient webTestClient

    def "application should start and should response to health-check"() {
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

package com.rk.springkotlinmavendockerapp

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
class SpringKotlinMavenDockerAppSpec extends Specification {

    @Autowired
    private MockMvc mockMvc

    def "application should start and should response to health-check"() {
        expect:
        mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
    }
}

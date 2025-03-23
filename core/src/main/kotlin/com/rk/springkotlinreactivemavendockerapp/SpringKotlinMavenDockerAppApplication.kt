package com.rk.springkotlinreactivemavendockerapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotlinMavenDockerAppApplication

fun main(args: Array<String>) {
	runApplication<SpringKotlinMavenDockerAppApplication>(*args)
}

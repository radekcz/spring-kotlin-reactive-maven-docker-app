package com.rk.springkotlinreactivemavendockerapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringKotlinReactiveMavenDockerAppApplication

fun main(args: Array<String>) {
	runApplication<SpringKotlinReactiveMavenDockerAppApplication>(*args)
}
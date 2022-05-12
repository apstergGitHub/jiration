package com.jiration.core

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan("com.jiration")
class ApplicationRunner {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("Starting app")
            SpringApplication.run(ApplicationRunner::class.java, *args)
        }
    }
}
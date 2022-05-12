package com.jiration.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EffortRetriever {

    @GetMapping("/estimate/time")
    fun retrieveEffort(): String {
        println("here")
        return "Any"
    }
}
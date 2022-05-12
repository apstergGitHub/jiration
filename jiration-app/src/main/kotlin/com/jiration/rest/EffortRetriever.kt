package com.jiration.rest

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class EffortRetriever {

    @GetMapping("/estimate/time")
    fun retrieveEffort(@RequestParam project: String, @RequestParam effort: Int): String {
        return "Project: $project -- effort: $effort"
    }
}
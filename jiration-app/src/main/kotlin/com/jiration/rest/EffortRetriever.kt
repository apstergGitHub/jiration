package com.jiration.rest

import com.atlassian.jira.rest.client.api.JiraRestClientFactory
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI


@RestController
class EffortRetriever {

    @GetMapping("/estimate/time")
    fun retrieveEffort(@RequestParam project: String, @RequestParam effort: Int): String {
        //com.atlassian.query.Query
        //com.atlassian.jira.issue.search.SearchRequest
        //com.atlassian.jira.issue.Issue

        val factory: JiraRestClientFactory = AsynchronousJiraRestClientFactory()
        val restClient =
            factory.createWithAuthenticationHandler(URI("CHANGE_ME"), TokenAuthenticationHandler())

        val result = restClient.searchClient
            .searchJql("issue=HIRO-2525")
            .claim()
            .issues
            .fold("") { acc, issue -> acc + "\n" + issue }

        return result
    }
}
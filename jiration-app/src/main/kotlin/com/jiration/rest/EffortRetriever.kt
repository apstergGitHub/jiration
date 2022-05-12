package com.jiration.rest

import com.atlassian.jira.rest.client.api.JiraRestClientFactory
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory
import com.jiration.calc.AverageCalculator
import com.jiration.persistence.Issue
import com.jiration.persistence.Persistence
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import java.time.Duration
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


@RestController
class EffortRetriever {

    private val repository = Persistence()
    private var previousQueryTimestamp: Instant = ZonedDateTime.of(2000, 1, 1, 0, 0, 0, 0, ZoneId.systemDefault()).toInstant()
    private val averageCalculator = AverageCalculator()

    @GetMapping("/estimate/time")
    fun retrieveEffort(@RequestParam project: String, @RequestParam effort: Int): String {
        //com.atlassian.query.Query
        //com.atlassian.jira.issue.search.SearchRequest
        //com.atlassian.jira.issue.Issue

        val factory: JiraRestClientFactory = AsynchronousJiraRestClientFactory()
        val restClient =
            factory.createWithAuthenticationHandler(URI("http://10.23.145.194:8080"), TokenAuthenticationHandler())


        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val previousQueryTimeInJqlFormat = formatter.format(previousQueryTimestamp.atZone(ZoneId.systemDefault()))
        val nowTimestamp = Instant.now()
        val currentQueryTimeInJqlFormat = formatter.format(nowTimestamp.atZone(ZoneId.systemDefault()))
        restClient.searchClient
            .searchJql("project = $project AND resolved > \"$previousQueryTimeInJqlFormat\" AND resolved <=  \"$currentQueryTimeInJqlFormat\" ")
            .claim()
            .issues
            .filter { it.getFieldByName("Story Points")?.value?.toString()?.toDouble()?.toInt() == effort }
            .forEach { repository.updateMetrics(Issue(effort, it.key, Duration.ofDays(1))) }

        val aggregatedIssues = repository.getMetrics(effort)
        val averageTime = averageCalculator.averageTime(aggregatedIssues)

        previousQueryTimestamp = nowTimestamp

        return "Project: $project\n" +
                "effort: $effort\n" +
                "issues: ${aggregatedIssues.issues}\n" +
                "average completion in days: $averageTime "
    }
}
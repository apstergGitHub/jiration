package com.jiration.persistence

import java.time.Duration

class Persistence {
    private val storage = mutableMapOf<Int, AggregatedIssues>()

//    ( n * a + v ) / (n + 1);
//    Where n is our old count, a is our old average, and v is our new value.
fun updateMetrics(issue: Issue) {
    storage.apply {
        this.putIfAbsent(issue.effort, AggregatedIssues(issue.effort, setOf(issue.name), issue.duration))
            ?.let {
                AggregatedIssues(
                    issue.effort, it.issues.plus(issue.name),
                    it.timeEstimate.plus(issue.duration)
                )
            }
            ?.also { this[issue.effort] = it }
    }
}

    fun getMetrics(effort: Int): AggregatedIssues = storage[effort]!!
}


data class AggregatedIssues(val effort: Int, val issues: Set<String>, val timeEstimate: Duration)
data class Issue(val effort: Int, val name: String, val duration: Duration)
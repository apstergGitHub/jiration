package com.jiration.calc

import com.jiration.persistence.AggregatedIssues

class AverageCalculator {

    fun averageTime(issues: AggregatedIssues) = issues.timeEstimate.toDays() / issues.issues.size
}
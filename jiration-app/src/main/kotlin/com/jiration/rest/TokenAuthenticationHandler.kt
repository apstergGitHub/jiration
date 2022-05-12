package com.jiration.rest

import com.atlassian.httpclient.api.Request
import com.atlassian.jira.rest.client.api.AuthenticationHandler

class TokenAuthenticationHandler: AuthenticationHandler {

    override fun configure(builder: Request.Builder) {
        builder.setHeader("Authorization", "Bearer CHANGE_ME")
    }
}
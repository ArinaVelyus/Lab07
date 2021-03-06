package com.velus.lab6.common.application;

import com.velus.lab6.common.exception.RequestFailureException;
import com.velus.lab6.common.request.Request;
import com.velus.lab6.common.response.Response;
import com.velus.lab6.common.types.Authentication;

public abstract class Application {

    private Authentication authentication = Authentication.empty;

    public void setAuthentication(Authentication authentication) {
        this.authentication = authentication;
    }

    public Authentication getAuthentication() {
        return this.authentication;
    }

    public abstract <RequestType extends Request> Response getResponse(RequestType request) throws RequestFailureException;
}

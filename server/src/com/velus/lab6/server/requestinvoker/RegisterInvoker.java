package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.common.request.RegisterRequest;
import com.velus.lab6.common.response.RegisterResponse;
import com.velus.lab6.common.response.Response;
import com.velus.lab6.common.types.Authentication;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.AuthenticationProvider;

public class RegisterInvoker extends RequestInvoker<RegisterRequest> {
    public RegisterInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    protected Response buildResponse(RegisterRequest request) {
        Authentication authentication = request.getAuthentication();
        AuthenticationProvider authenticationProvider = getAuthenticationProvider();
        authenticationProvider.register(authentication);
        return new RegisterResponse(authentication);
    }

    @Override
    public Class<RegisterRequest> getRequestClass() {
        return RegisterRequest.class;
    }
}

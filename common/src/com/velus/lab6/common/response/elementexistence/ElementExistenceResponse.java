package com.velus.lab6.common.response.elementexistence;

import com.velus.lab6.common.response.Response;

public class ElementExistenceResponse extends Response {
    private final Boolean hasElementExisted;

    public ElementExistenceResponse(Boolean hasElementExisted) {
        this.hasElementExisted = hasElementExisted;
    }

    public boolean hasElementExisted() {
        return hasElementExisted;
    }
}

package com.velus.lab6.server.response.collectionemptiness;

import com.velus.lab6.common.response.Response;

public class CollectionEmptinessResponse extends Response {
    private final Boolean isCollectionEmpty;

    public CollectionEmptinessResponse(Boolean isCollectionEmpty) {
        this.isCollectionEmpty = isCollectionEmpty;
    }

    public boolean isCollectionEmpty() {
        return this.isCollectionEmpty;
    }
}

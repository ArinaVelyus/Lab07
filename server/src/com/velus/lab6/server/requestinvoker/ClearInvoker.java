package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.server.request.ClearRequest;
import com.velus.lab6.server.response.collectionemptiness.ClearResponse;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.CollectionProvider;

public class ClearInvoker extends RequestInvoker<ClearRequest> {

    public ClearInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public ClearResponse buildResponse(ClearRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.isEmpty()) {
            return new ClearResponse(true);
        } else {
            collectionProvider.clear();
            return new ClearResponse(false);
        }
    }

    @Override
    public Class<ClearRequest> getRequestClass() {
        return ClearRequest.class;
    }
}

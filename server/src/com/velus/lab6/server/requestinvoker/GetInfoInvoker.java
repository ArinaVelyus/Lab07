package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.common.request.GetInfoRequest;
import com.velus.lab6.common.response.GetInfoResponse;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.CollectionProvider;

public class GetInfoInvoker extends RequestInvoker<GetInfoRequest> {

    public GetInfoInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetInfoResponse buildResponse(GetInfoRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        return new GetInfoResponse(
                collectionProvider.getType(),
                collectionProvider.getInitializationDate(),
                collectionProvider.getElementsCount()
        );
    }

    @Override
    public Class<GetInfoRequest> getRequestClass() {
        return GetInfoRequest.class;
    }
}

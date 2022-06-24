package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.common.request.GetStatusDescendingRequest;
import com.velus.lab6.common.response.GetStatusDescendingResponse;
import com.velus.lab6.common.types.Status;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.CollectionProvider;

import java.util.List;

public class GetStatusDescendingInvoker extends RequestInvoker<GetStatusDescendingRequest> {

    public GetStatusDescendingInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetStatusDescendingResponse buildResponse(GetStatusDescendingRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        List<Status> statusList = collectionProvider.getStatusListDescending();
        return new GetStatusDescendingResponse(statusList);
    }

    @Override
    public Class<GetStatusDescendingRequest> getRequestClass() {
        return GetStatusDescendingRequest.class;
    }
}

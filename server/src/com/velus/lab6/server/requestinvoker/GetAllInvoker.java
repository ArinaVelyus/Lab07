package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.common.request.GetAllRequest;
import com.velus.lab6.common.response.GetAllResponse;
import com.velus.lab6.common.types.Worker;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.CollectionProvider;

import java.util.List;

public class GetAllInvoker extends RequestInvoker<GetAllRequest> {

    public GetAllInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetAllResponse buildResponse(GetAllRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        List<Worker> elements = collectionProvider.getAll();
        return new GetAllResponse(elements);
    }

    @Override
    public Class<GetAllRequest> getRequestClass() {
        return GetAllRequest.class;
    }
}

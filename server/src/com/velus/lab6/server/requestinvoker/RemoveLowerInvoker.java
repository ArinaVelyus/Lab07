package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.server.request.RemoveLowerRequest;
import com.velus.lab6.server.RemoveLowerResponse;
import com.velus.lab6.common.types.Worker;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.CollectionProvider;

public class RemoveLowerInvoker extends RequestInvoker<RemoveLowerRequest> {

    public RemoveLowerInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public RemoveLowerResponse buildResponse(RemoveLowerRequest request) {
        Worker element = request.getElement();
        CollectionProvider collectionProvider = getCollectionProvider(request);
        if (collectionProvider.isEmpty()) {
            return new RemoveLowerResponse(false);
        } else {
            collectionProvider.removeLower(element);
            return new RemoveLowerResponse(true);
        }
    }

    @Override
    public Class<RemoveLowerRequest> getRequestClass() {
        return RemoveLowerRequest.class;
    }
}

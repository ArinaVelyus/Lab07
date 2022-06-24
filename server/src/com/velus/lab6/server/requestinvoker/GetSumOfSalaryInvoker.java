package com.velus.lab6.server.requestinvoker;

import com.velus.lab6.common.request.GetSumOfSalaryRequest;
import com.velus.lab6.common.response.GetSumOfSalaryResponse;
import com.velus.lab6.server.ServerApplication;
import com.velus.lab6.server.database.CollectionProvider;

public class GetSumOfSalaryInvoker extends RequestInvoker<GetSumOfSalaryRequest> {

    public GetSumOfSalaryInvoker(ServerApplication application) {
        super(application);
    }

    @Override
    public GetSumOfSalaryResponse buildResponse(GetSumOfSalaryRequest request) {
        CollectionProvider collectionProvider = getCollectionProvider(request);
        Double sumOfSalary = collectionProvider.getSumOfSalary();
        return new GetSumOfSalaryResponse(sumOfSalary);
    }

    @Override
    public Class<GetSumOfSalaryRequest> getRequestClass() {
        return GetSumOfSalaryRequest.class;
    }
}

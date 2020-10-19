package com.yicj.seata.web.service;

import com.yicj.seata.common.dto.OrderRequest;
import com.yicj.seata.common.response.ObjectResponse;

public interface IRestOrderService {

    ObjectResponse handleBusiness(OrderRequest orderRequest) throws Exception;
}

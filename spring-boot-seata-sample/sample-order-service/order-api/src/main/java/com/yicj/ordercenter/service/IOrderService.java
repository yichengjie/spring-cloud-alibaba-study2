package com.yicj.ordercenter.service;

import com.yicj.seata.common.dto.OrderDto;
import com.yicj.seata.common.response.ObjectResponse;

public interface IOrderService {
    /**
     * 创建订单
     * @param orderDto
     * @return
     */
    ObjectResponse<OrderDto> createOrder(OrderDto orderDto);
}
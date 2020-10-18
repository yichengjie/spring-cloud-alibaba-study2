package com.yicj.ordercenter.service.impl;

import com.yicj.ordercenter.dao.order.TblOrderMapper;
import com.yicj.ordercenter.service.IOrderService;
import com.yicj.seata.common.dto.OrderDto;
import com.yicj.seata.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    private TblOrderMapper orderMapper ;


    @Override
    public ObjectResponse<OrderDto> createOrder(OrderDto orderDto) {



        return null;
    }
}

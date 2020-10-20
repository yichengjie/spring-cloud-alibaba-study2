package com.yicj.seata.web.service.impl;

import com.yicj.ordercenter.service.IOrderService;
import com.yicj.repocenter.service.IRepoService;
import com.yicj.seata.common.constants.ResCode;
import com.yicj.seata.common.dto.OrderDto;
import com.yicj.seata.common.dto.OrderRequest;
import com.yicj.seata.common.dto.ProductDto;
import com.yicj.seata.common.response.ObjectResponse;
import com.yicj.seata.web.service.IRestOrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RestOrderServiceImpl implements IRestOrderService {
    @Reference(version = "1.0.1", group = "yicj")
    private IRepoService repoService;
    @Reference(version = "1.0.1", group = "yicj")
    private IOrderService orderService;

    @GlobalTransactional(timeoutMills = 30000, name = "sample-rest-web")
    @Override
    public ObjectResponse handleBusiness(OrderRequest orderRequest) throws Exception {
        log.info("开始全局事务: xid = {}", RootContext.getXID());
        log.info("begin order: "+orderRequest);
        //1. 扣减库存
        ProductDto productDto=new ProductDto();
        productDto.setProductCode(orderRequest.getProductCode());
        productDto.setCount(orderRequest.getCount());
        ObjectResponse repoRes=repoService.decreaseRepo(productDto);
        //2. 创建订单
        OrderDto orderDto=new OrderDto();
        orderDto.setUserId(orderRequest.getUserId());
        orderDto.setOrderAmount(orderRequest.getAmount());
        orderDto.setOrderCount(orderRequest.getCount());
        orderDto.setProductCode(orderRequest.getProductCode());
        ObjectResponse orderRes=orderService.createOrder(orderDto);
        if(orderRequest.getProductCode().equals("GP2020020202")){
            throw new Exception("系统异常");
        }
        ObjectResponse response=new ObjectResponse();
        response.setMsg(ResCode.SUCCESS.getMessage());
        response.setCode(ResCode.SUCCESS.getCode());
        response.setData(orderRes.getData());
        return response;
    }
}
package com.yicj.ordercenter.service.impl;

import com.yicj.accountcenter.service.IAccountService;
import com.yicj.ordercenter.convert.OrderConvert;
import com.yicj.ordercenter.dao.order.TblOrderMapper;
import com.yicj.ordercenter.domain.entity.order.TblOrder;
import com.yicj.ordercenter.service.IOrderService;
import com.yicj.seata.common.constants.ResCode;
import com.yicj.seata.common.dto.AccountDto;
import com.yicj.seata.common.dto.OrderDto;
import com.yicj.seata.common.response.ObjectResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.UUID;

@Slf4j
@Service(version = "1.0.1", group = "yicj")
public class OrderServiceImpl implements IOrderService {
    @Reference(version = "1.0.1", group = "yicj")
    private IAccountService accountService ;
    @Autowired
    private  TblOrderMapper orderMapper ;
    @Autowired
    private OrderConvert orderConvert ;

    @Override
    public ObjectResponse<OrderDto> createOrder(OrderDto orderDto) {
        ObjectResponse response=new ObjectResponse();
        try {
            //账户扣款
            AccountDto accountDto = new AccountDto();
            accountDto.setUserId(orderDto.getUserId());
            accountDto.setBalance(orderDto.getOrderAmount());
            ObjectResponse accountRes = accountService.decreaseAccount(accountDto);
            //创建订单
            TblOrder order=orderConvert.dto2Order(orderDto);
            order.setOrderNo(UUID.randomUUID().toString());
            orderMapper.createOrder(order);
            //判断扣款状态(判断可以前置）
            if(accountRes.getCode()!= ResCode.SUCCESS.getCode()){
                response.setMsg(ResCode.FAILED.getMessage());
                response.setCode(ResCode.FAILED.getCode());
                return response;
            }
            response.setMsg(ResCode.SUCCESS.getMessage());
            response.setCode(ResCode.SUCCESS.getCode());
        }catch (Exception e){
            log.error("createOrder Occur Exception:"+e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage()+"-"+e.getMessage());
        }
        return response;
    }
}

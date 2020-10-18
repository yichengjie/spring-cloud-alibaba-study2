package com.yicj.ordercenter.convert;

import com.yicj.ordercenter.domain.entity.order.TblOrder;
import com.yicj.seata.common.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderConvert {
    @Mappings({
        @Mapping(source = "orderCount",target = "count"),
        @Mapping(source = "orderAmount",target = "amount")
    })

    TblOrder dto2Order(OrderDto orderDto);
}
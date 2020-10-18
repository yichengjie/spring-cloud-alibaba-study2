package com.yicj.ordercenter.dao.order;

import com.yicj.ordercenter.domain.entity.order.TblOrder;
import tk.mybatis.mapper.common.Mapper;

public interface TblOrderMapper extends Mapper<TblOrder> {
    /**
     * 创建订单
     * @param order
     */
    void createOrder(TblOrder order);
}
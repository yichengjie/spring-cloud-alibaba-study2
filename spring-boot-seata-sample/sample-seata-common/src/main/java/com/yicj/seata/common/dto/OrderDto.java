package com.yicj.seata.common.dto;

import lombok.Data;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class OrderDto implements Serializable {
    private static final long serialVersionUID = -1233077570826468556L;
    private String orderNo;
    private String userId;
    private String productCode;
    private Integer orderCount;
    private BigDecimal orderAmount;
}
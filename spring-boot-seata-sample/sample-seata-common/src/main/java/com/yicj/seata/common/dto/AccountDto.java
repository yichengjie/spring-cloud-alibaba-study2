package com.yicj.seata.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class AccountDto implements Serializable {
    private static final long serialVersionUID = 428434492100190769L;
    private Integer id;
    private String userId;
    private BigDecimal balance;
}
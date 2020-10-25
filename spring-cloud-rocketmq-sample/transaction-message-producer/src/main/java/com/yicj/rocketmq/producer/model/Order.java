package com.yicj.rocketmq.producer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Order {
    private String orderId ;
    private String address ;
}

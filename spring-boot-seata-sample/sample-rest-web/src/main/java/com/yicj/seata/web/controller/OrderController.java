package com.yicj.seata.web.controller;

import com.yicj.seata.common.dto.OrderRequest;
import com.yicj.seata.common.response.ObjectResponse;
import com.yicj.seata.web.service.IRestOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {
    private final IRestOrderService restOrderService;

    @PostMapping("/order")
    public ObjectResponse order(@RequestBody OrderRequest orderRequest) throws Exception {
        return restOrderService.handleBusiness(orderRequest);
    }
}

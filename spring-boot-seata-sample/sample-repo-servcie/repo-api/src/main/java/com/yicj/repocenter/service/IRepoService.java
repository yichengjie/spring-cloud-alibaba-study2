package com.yicj.repocenter.service;


import com.yicj.seata.common.dto.ProductDto;
import com.yicj.seata.common.response.ObjectResponse;

public interface IRepoService {
    /**
     * 扣减库存
     */
    ObjectResponse decreaseRepo(ProductDto productDto);
}

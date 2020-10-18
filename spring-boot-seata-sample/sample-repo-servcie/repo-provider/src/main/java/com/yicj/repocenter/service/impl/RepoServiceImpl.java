package com.yicj.repocenter.service.impl;

import com.yicj.repocenter.service.IRepoService;
import com.yicj.seata.common.dto.ProductDto;
import com.yicj.seata.common.response.ObjectResponse;
import org.apache.dubbo.config.annotation.Service;

@Service
public class RepoServiceImpl implements IRepoService {
    @Override
    public ObjectResponse decreaseRepo(ProductDto productDto) {
        return null;
    }
}

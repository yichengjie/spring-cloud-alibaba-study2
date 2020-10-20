package com.yicj.repocenter.service.impl;

import com.yicj.repocenter.dao.repo.TblRepoMapper;
import com.yicj.repocenter.service.IRepoService;
import com.yicj.seata.common.constants.ResCode;
import com.yicj.seata.common.dto.ProductDto;
import com.yicj.seata.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service(version = "1.0.1", group = "yicj")
public class RepoServiceImpl implements IRepoService {
    @Autowired
    private TblRepoMapper repoMapper;

    @Override
    public ObjectResponse decreaseRepo(ProductDto productDto) {
        log.info("开始全局事务: xid = {}", RootContext.getXID());
        ObjectResponse response=new ObjectResponse();
        try {
            int repo = repoMapper.decreaseRepo(productDto.getProductCode(), productDto.getCount());
            if(repo>0){
                response.setMsg(ResCode.SUCCESS.getMessage());
                response.setCode(ResCode.SUCCESS.getCode());
                return response;
            }
            response.setMsg(ResCode.FAILED.getMessage());
            response.setCode(ResCode.FAILED.getCode());
        }catch (Exception e){
            log.error("decreaseRepo Occur Exception:"+e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage()+"-"+e.getMessage());
        }
        return response;
    }
}

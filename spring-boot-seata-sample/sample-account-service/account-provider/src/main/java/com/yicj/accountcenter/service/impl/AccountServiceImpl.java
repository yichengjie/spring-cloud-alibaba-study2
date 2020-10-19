package com.yicj.accountcenter.service.impl;

import com.yicj.accountcenter.dao.account.TblAccountMapper;
import com.yicj.accountcenter.service.IAccountService;
import com.yicj.seata.common.constants.ResCode;
import com.yicj.seata.common.dto.AccountDto;
import com.yicj.seata.common.response.ObjectResponse;
import io.seata.core.context.RootContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service(version = "1.0.1", group = "yicj")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements IAccountService {

    private final TblAccountMapper accountMapper ;

    @Override
    public ObjectResponse decreaseAccount(AccountDto accountDto) {
        log.info("开始全局事务: xid = {}", RootContext.getXID());
        ObjectResponse response = new ObjectResponse() ;
        try {
            int rs = accountMapper.decreaseAccount(
                    accountDto.getUserId(), accountDto.getBalance().doubleValue()) ;
            if (rs >0){
                response.setMsg(ResCode.SUCCESS.getMessage());
                response.setCode(ResCode.SUCCESS.getCode());
                return response ;
            }
            response.setMsg(ResCode.FAILED.getMessage());
            response.setCode(ResCode.FAILED.getCode());
        }catch (Exception e){
            log.error("decreaseAccount Occur Exception :" ,e);
            response.setCode(ResCode.SYSTEM_EXCEPTION.getCode());
            response.setMsg(ResCode.SYSTEM_EXCEPTION.getMessage() +"-" + e.getMessage());
        }
        return response;
    }
}

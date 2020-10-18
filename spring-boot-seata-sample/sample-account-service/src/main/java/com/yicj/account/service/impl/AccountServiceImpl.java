package com.yicj.account.service.impl;

import com.yicj.account.dao.account.TblAccountMapper;
import com.yicj.account.service.IAccountService;
import com.yicj.seata.common.constants.ResCode;
import com.yicj.seata.common.dto.AccountDto;
import com.yicj.seata.common.response.ObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AccountServiceImpl implements IAccountService {

    private final TblAccountMapper accountMapper ;

    @Override
    public ObjectResponse decreaseAccount(AccountDto accountDto) {
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

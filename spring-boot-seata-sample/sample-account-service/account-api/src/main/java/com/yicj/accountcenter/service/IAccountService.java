package com.yicj.accountcenter.service;

import com.yicj.seata.common.dto.AccountDto;
import com.yicj.seata.common.response.ObjectResponse;

public interface IAccountService {

    ObjectResponse decreaseAccount(AccountDto accountDto) ;
}

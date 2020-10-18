package com.yicj.accountcenter.dao.account;

import com.yicj.accountcenter.domain.entity.account.TblAccount;
import tk.mybatis.mapper.common.Mapper;

public interface TblAccountMapper extends Mapper<TblAccount> {
    int decreaseAccount(String userId, Double balance) ;
}
package com.yicj.account.dao.account;

import com.yicj.account.domain.entity.account.TblAccount;
import tk.mybatis.mapper.common.Mapper;

public interface TblAccountMapper extends Mapper<TblAccount> {
    int decreaseAccount(String userId, Double balance) ;
}
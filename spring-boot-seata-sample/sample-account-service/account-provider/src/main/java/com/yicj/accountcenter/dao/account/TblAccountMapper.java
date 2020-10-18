package com.yicj.accountcenter.dao.account;

import com.yicj.accountcenter.domain.entity.account.TblAccount;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TblAccountMapper extends Mapper<TblAccount> {
    int decreaseAccount(@Param("userId") String userId, @Param("balance") Double balance);
}
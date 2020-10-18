package com.yicj.repocenter.dao.repo;

import com.yicj.repocenter.domain.entity.repo.TblRepo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface TblRepoMapper extends Mapper<TblRepo> {

    int decreaseRepo(@Param("productCode") String productCode, @Param("count") Integer count);
}
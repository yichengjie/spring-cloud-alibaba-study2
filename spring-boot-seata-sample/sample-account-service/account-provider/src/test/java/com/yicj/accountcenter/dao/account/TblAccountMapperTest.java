package com.yicj.accountcenter.dao.account;

import com.yicj.accountcenter.AccountCenterApplication;
import com.yicj.accountcenter.domain.entity.account.TblAccount;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AccountCenterApplication.class)
public class TblAccountMapperTest {
    @Resource
    private TblAccountMapper accountMapper ;

    @Test
    public void selectAll(){
        List<TblAccount> tblAccounts = accountMapper.selectAll();
        log.info("=====> info :{}", tblAccounts);
    }
}

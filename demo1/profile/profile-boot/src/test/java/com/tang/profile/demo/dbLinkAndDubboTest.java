package com.tang.profile.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tang.api.profile.AcctService;
import com.tang.api.profile.DemoUserService;
import com.tang.base.util.BaseCommonUtil;
import com.tang.base.util.pagehelp.PageHelp;
import com.tang.param.billing.AuditThemeParam;
import com.tang.profile.demo.testBase.DemoProfileApplicationStartTests;
import com.tang.profile.demoprovider.infrastrucrute.DAO.profile.IDemoUserDAO;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/13 <br>
 */
public class dbLinkAndDubboTest extends DemoProfileApplicationStartTests {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(dbLinkAndDubboTest.class);

    /**
     * < >
     */
    @Autowired
    private AcctService acctService;

    @Autowired
    DemoUserService demoUserService;

    @Autowired IDemoUserDAO demoUserDAO;

//        @Test
    public void rua4() throws Exception {
            List<Long> ids = demoUserDAO.selectUserWithoutDetail(2000, 2);
            logger.info("size is {}", ids.size());
            logger.info("detail is {}", BaseCommonUtil.objectToJsonString(ids));
    }

    // 扫描用户。给没有detail的添加detail
    @Test
    public void rua2() throws Exception {
        List<Long> ids = new ArrayList<>();
        ids.add(1L);
        demoUserService.createDefaultUserDetail(false, ids,null);

    }

    // 创建用户，现在一共2536个
//    @Test
    public void rua() throws Exception {
        for (int i = 3764; i < 4764; i++) {
            demoUserService.createNewUser("dec", "dec", i);
        }

    }

    // @Test
    public void selectUserByIdTest() throws Exception {
        AuditThemeParam ret = acctService.dbLinkTest(10256L);
        Long ret2 = acctService.querySizeOfTableAcct();

        logger.info("------------selectUserByIdTest start----------------");
        logger.info("result is:" + ret.getThemeName());
        logger.info("ret2 size is:" + ret2.toString());
    }
}

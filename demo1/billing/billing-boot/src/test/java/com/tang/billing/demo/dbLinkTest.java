package com.tang.billing.demo;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tang.api.billing.AuditThemeService;
import com.tang.base.util.BaseCommonUtil;
import com.tang.billing.demo.testBase.DemoBillingApplicationStartTests;
import com.tang.param.billing.AuditThemeParam;

/**
 * < 这是一个测试数据库是否能通的测试类 ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/12 <br>
 */
public class dbLinkTest extends DemoBillingApplicationStartTests {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(dbLinkTest.class);

    /**
     * < >
     */
    @Autowired
    private AuditThemeService auditThemeService;

    @Test
    public void selectUserByIdTest() throws Exception {
        AuditThemeParam ret = auditThemeService.queryById(10L);
        List<AuditThemeParam> ret2 = auditThemeService.queryAllTheme();
        String useLocalCache = System.getProperty("LocalCache", "true");

        logger.info("------------selectUserByIdTest start----------------");
        logger.info("result is:" + ret.getThemeName());
        logger.info("ret2 size is:" + ret2.size());
        logger.info("useLocalCache is {} hahahaaha", useLocalCache);

        logger.info("------------===========----------------");
        String rua = "userId/doc/auto/en_US/timeStamp_random";
        String rua2 = "userId/doc/auto/en_US/timeStamp_random,userId/doc/auto/zh_CH/timeStamp_random";
        String[] hahha = rua.split(",");
        String[] hahha2 = rua2.split(",");

        String arGlTypeMasks = null;
        String[] arGlTypeMaskArr = arGlTypeMasks.split(",");
        logger.info("============================================");
        logger.info("========is {}", BaseCommonUtil.objectToJsonString(arGlTypeMaskArr));

    }
}

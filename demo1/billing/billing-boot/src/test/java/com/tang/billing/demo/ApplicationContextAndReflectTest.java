package com.tang.billing.demo;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tang.api.billing.AuditThemeService;
import com.tang.base.util.ApplicationContextHolder;
import com.tang.base.util.BaseCommonUtil;
import com.tang.base.util.DateUtil;
import com.tang.base.util.ReflectUtil;
import com.tang.billing.demo.testBase.DemoBillingApplicationStartTests;
import com.tang.param.billing.AuditThemeParam;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/10/9 <br>
 */
public class ApplicationContextAndReflectTest extends DemoBillingApplicationStartTests {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(ApplicationContextAndReflectTest.class);

    @Autowired
    AuditThemeService auditThemeService;

    String funcName1 = "queryAllTheme";

    // String funcName2 = "queryById";
    String funcName3 = "deleteById";

    String funcName4 = "createTheme";

    String funcName5 = "updateTheme";

    String classPath = "com.tang.billing.demoprovider.serviceimpl.AuditThemServiceImpl";

    @Test
    public void ReflectTest() throws Exception {
        logger.info("------------ReflectTest start----------------");

//        // 测试bean对象的空参方法 -------------------------测试OK
//        List<AuditThemeParam> ret = ReflectUtil.methodInvokeBean(classPath, funcName1);
//        logger.info("===ret===:" + BaseCommonUtil.objectToJsonString(ret));

//        // 测试newInstance对象的空参方法 --------------------------测试OK
//        List<AuditThemeParam> ret2 = ReflectUtil.methodInvoke(classPath, funcName1);
//        logger.info("===ret2===::" + BaseCommonUtil.objectToJsonString(ret2));

//        // 测试删除服务，入参为Long,无返回值 ---测试OK
//        Long themeIdToBeDel = 10202L;
//        Object[] param1 = new Object[] {
//            themeIdToBeDel
//        };
//        ReflectUtil.methodInvoke(auditThemeService, funcName3, param1);

        // 测试新增服务，多个入参，返回一个包装类型 -------------------------这个方法有bug，解析不了参数，再定位下
        String themeName = "make up theme name";
        String comments = "fake";
        Long themeType = 0L;
        Date createDate = DateUtil.getNowTimeDate();
        Object[] param2 = new Object[] {
            themeName, comments, themeType, createDate
        };
        AuditThemeParam ret3 = ReflectUtil.methodInvoke(auditThemeService, funcName4, param2);
        logger.info("===ret3===::" + BaseCommonUtil.objectToJsonString(ret3));

//        // 测试修改服务，多个入参，包装成包装类型 -------------------------测试ok
//        AuditThemeParam paramToBeUpdate = new AuditThemeParam();
//        paramToBeUpdate.setThemeId(10203L);
//        paramToBeUpdate.setThemeName("change to rua");
//        paramToBeUpdate.setCode("1239999677777777777777");
//        paramToBeUpdate.setStateDate(DateUtil.getNowTimeDate());
//        paramToBeUpdate.setComments("fake comments fake fake fake fake fake");
//        Object[] param3 = new Object[] {
//            paramToBeUpdate
//        };
//
//        AuditThemeService auditThemeService2 = (AuditThemeService) ApplicationContextHolder.getBeanByType(AuditThemeService.class);
//        ReflectUtil.methodInvoke(auditThemeService2, funcName5, param3);   //两种bean都可以
//        ReflectUtil.methodInvoke(auditThemeService, funcName5, param3);
    }

    // @Test
    public void ApplicationContextTest() throws Exception {
        logger.info("------------selectUserByIdTest start----------------");
        AuditThemeService auditThemeService = (AuditThemeService) ApplicationContextHolder.getBeanByType(AuditThemeService.class);
        if (auditThemeService != null) {
            AuditThemeParam ret = auditThemeService.queryById(10L);
            logger.info("------------selectUserByIdTest end----------------");
            logger.info("result is:" + ret.getThemeName());
        }
        else {
            logger.info("------------null ha----------------");
        }

    }

}

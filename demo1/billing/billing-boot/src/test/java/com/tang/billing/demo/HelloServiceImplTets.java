package com.tang.billing.demo;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tang.base.util.BaseCommonUtil;
import com.tang.billing.demo.testBase.DemoBillingApplicationStartTests;
import com.tang.param.billing.AuditThemeParam;
import com.tang.param.billing.GetDataInputParam;

/**
 * < 测试类种的所有方法 ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/26 <br>
 */
public class HelloServiceImplTets extends DemoBillingApplicationStartTests {
    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(HelloServiceImplTets.class);

    /**
     * < >
     */
    @Autowired
    private com.tang.api.billing.HelloService helloService;

    @Test
    public void rua() throws Exception {
        Map<Object, Object> map = new HashMap<>();
        AuditThemeParam param1 = new AuditThemeParam();
        GetDataInputParam GDParam1 = new GetDataInputParam();
        GDParam1.setInput("input");
        param1.setGDParam(GDParam1);
        param1.setThemeId(1L);
        param1.setThemeName("rua");
        logger.info("param1 hashCode is {}", param1.hashCode());
        logger.info("param1 is {}", BaseCommonUtil.objectToJsonString(param1));
        GetDataInputParam GDParam2 = param1.getGDParam();
        GDParam2.setUserId(2222L);
        logger.info("param1 is {}", BaseCommonUtil.objectToJsonString(param1));
        logger.info("GDParam2 is {}", BaseCommonUtil.objectToJsonString(GDParam2));


    }

    private void func(AuditThemeParam param, int a, String rua) {
        param.setState("A");
        a = 8;
        rua = "hahahha";
    }

    // @Test
    public void helloServiceTest() throws Exception {
        helloService.sayHello("say hello");
        helloService.testProfileLink("profile link");
        helloService.testRepository("repository try");

    }
}

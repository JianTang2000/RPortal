package com.tang.billing.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tang.billing.demo.testBase.DemoBillingApplicationStartTests;

/**
 * < 这是一个测试dubbo调用是否能通的测试类 ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/12 <br>
 */
public class serviceLinkTest extends DemoBillingApplicationStartTests {

    /**
     * < api里对外暴露的接口，也可以注入实现类，不过实现类应该就仅仅限于本中心了 >
     */
    @Autowired
    private com.tang.api.billing.HelloService helloService;

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(serviceLinkTest.class);

    /**
     * < 这个测试类测试了三方面 第一是没配置日志级别默认打的是哪种级别----info 第二是注入的方式调本中心的服务----ok 第三是注入的方式调本中心的服务，服务里面请求了其他中心的服务 ----Ok> <br>
     * 
     * @auther: tang.jian
     * @throws Exception <br>
     */
    @Test
    public void dubboServiceLinkTest() throws Exception {
        String a = " func: sayHello, service: HelloService, link: billing only ";
        String b = " func: testProfileLink, service: HelloService, link: billing&profile ";
        logger.info("------------debug dubboServiceLinkTest start----------------");
        logger.debug("------------debug dubboServiceLinkTest start----------------");
        logger.info("result is:" + helloService.sayHello(a));
        helloService.testProfileLink(b);
    }
}

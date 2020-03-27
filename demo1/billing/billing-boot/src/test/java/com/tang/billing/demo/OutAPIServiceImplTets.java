package com.tang.billing.demo;


import com.tang.api.billing.OutAPIService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tang.billing.demo.testBase.DemoBillingApplicationStartTests;

/**
 * < 测试类种的所有方法 ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/26 <br>
 */
public class OutAPIServiceImplTets extends DemoBillingApplicationStartTests {
    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(OutAPIServiceImplTets.class);

    /**
     * < >
     */
    @Autowired
    private OutAPIService outAPIService;

    @Test
    public void rua() throws Exception {
        logger.info("hello");


    }

}

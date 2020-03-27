package com.tang.billing.demoprovider.serviceimpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.api.billing.HelloService;
import com.tang.api.profile.HelloServiceProf;

/**
 * < >
 */
@Service("HelloService")
public class HelloServiceImpl implements HelloService {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    /**
     * < 其他中心的dubbo服务的注入 >
     */
    @Autowired
    HelloServiceProf helloServiceProf;

    @Override
    public String sayHello(String name) {
        String showInfo =
            "========================================================\n" +
                "center    :billing\n" +
                "type      :Spring service\n" +
                "package   :com.tang.billing.demoprovider.serviceimpl.HelloServiceImpl\n" +
                "function  :sayHello\n" +
                "desc      :show parameter only\n" +
                "info      :" + name+"\n" +
                "========================================================";
        logger.info(showInfo);
        return showInfo;
    }

    @Override
    public void testProfileLink(String info) {
        logger.info(helloServiceProf.sayHello(info));
    }

    /**
     * < 注入调用Repository > <br>
     *
     * @param info < >
     * @auther: tang.jian
     */
    @Override
    public void testRepository(String info) {
    }

}
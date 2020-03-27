package com.tang.billing.demoprovider.serviceimpl;

import com.tang.param.billing.Iris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.api.billing.OutAPIService;
import com.tang.api.profile.HelloServiceProf;

import java.util.List;

/**
 * < >
 */
@Service("HelloService")
public class OutAPIServiceImpl implements OutAPIService {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(OutAPIServiceImpl.class);

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
    public List<Iris> irisTree(List<Iris> param) {
        return helloServiceProf.irisTree(param);
    }


}
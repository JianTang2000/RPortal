package com.tang.billing.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

//
@SpringBootApplication
// 定义dubbo服务配置文件的路径，多个用逗号分隔
@ImportResource(locations = {
    "classpath*:dubbo/demo-billing-*.xml", "classpath*:applicationContext.xml"
})
// 定义非dubbo服务，但也要注册bean的文件的包的扫描路径，多个用逗号分隔
// 考虑到后面应该会把mybatis的配置放到这个扫描里面
@ComponentScan(basePackages = {
    "com.tang.billing.demoprovider","com.tang.base"
})
public class DemoBillingApplicationStart extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DemoBillingApplicationStart.class);
    }

    public static void main(String[] args) {
        /**
         * Logger.
         */
        final Logger logger = LoggerFactory.getLogger(EmbeddedZooKeeper.class);
        logger.debug("========================================================");
        logger.debug("======================BillingApplication============================");
        logger.debug("========================================================");
        // start embedded zookeeper server
        // 这里的EmbeddedZooKeeper的port，我暂时的理解是这个服务的端口，
        // ZK用的是2182，所以这里如果也用2182会占用
        new EmbeddedZooKeeper(2183, false).start();
        SpringApplication.run(DemoBillingApplicationStart.class, args);
    }

}


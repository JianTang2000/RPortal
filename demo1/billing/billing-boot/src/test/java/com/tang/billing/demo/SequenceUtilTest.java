package com.tang.billing.demo;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tang.api.profile.HelloServiceProf;
import com.tang.base.util.ApplicationContextHolder;
import com.tang.base.util.sequenceutil.SequenceUtil;
import com.tang.billing.demo.testBase.DemoBillingApplicationStartTests;
import com.tang.billing.demoprovider.infrastrucrute.model.AuditThemeDto;

/**
 * < sequence测试类，会制造两种场景，1一个业务中心并发请求获得序列，2两个业务中心一起请求获取序列 ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/10/16 <br>
 */
public class SequenceUtilTest extends DemoBillingApplicationStartTests {
    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(SequenceUtilTest.class);

    @Test
    public void fileFound() throws InterruptedException {
        logger.info("************===============************");
        HelloServiceProf helloServiceProf = (HelloServiceProf) ApplicationContextHolder.getBeanByName("HelloServiceProf");
        List<Thread> threadGroup1 = new ArrayList<>();
        for (int i = 1; i < 20; i++) {
            SequenceUtilTest.MyThread myThread = new SequenceUtilTest.MyThread("myThread" + String.valueOf(i), i, helloServiceProf);
            threadGroup1.add(myThread);
        }
        for (Thread thread : threadGroup1) {
            thread.start();
        }
        // DENG
        // 休眠
        Thread.sleep(100);
        // TING
        for (Thread thread : threadGroup1) {
            thread.join();
        }
    }

    public class MyThread extends Thread {
        private int i;

        private HelloServiceProf helloServiceProf;

        public MyThread(String nameOfParam, int i, HelloServiceProf helloServiceProf) {
            super(nameOfParam);
            this.i = i;
            this.helloServiceProf = helloServiceProf;
        }

        @Override
        public void run() {
            Long seq = SequenceUtil.next("BMC_AUDIT_RULE_JOB_RELA_SEQ");
            Long seq2 = this.helloServiceProf.getSeq("BMC_AUDIT_RULE_JOB_RELA_SEQ");
            String infoma = "thread number:" + String.valueOf(i) + "; billing seq" + String.valueOf(seq) + "; prof seq" + String.valueOf(seq2);
            logger.info("seq ret is {}", infoma);
            Object ob  = new AuditThemeDto();
        }
    }
}

package com.tang.billing.demoprovider.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.tang.billing.demoprovider.domain.AcctRepository;

/**
 * < ><br>
 *
 * @author tang.jian<br>
 * @CreateDate 2018/9/19 <br>
 */

@Repository("acctRepositoryImpl")
public class AcctRepositoryImpl implements AcctRepository {

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(AcctRepositoryImpl.class);

    /**
     * < 注入其他服务的bean，展示info > <br>
     *
     * @param info < 待展示的信息 >
     * @auther: tang.jian
     */
    @Override
    public void showHelloInfo(String info) {
        String showInfo =
            "========================================================\n" +
                "center    :billing\n" +
                "type      :Spring Repository\n" +
                "package   :com.tang.billing.demoprovider.domain.AcctRepository.AcctRepositoryImpl\n" +
                "function  :showHelloInfo\n" +
                "desc      :show parameter only\n" +
                "info      :" + info+"\n" +
                "========================================================";
        logger.info(showInfo);
    }
}

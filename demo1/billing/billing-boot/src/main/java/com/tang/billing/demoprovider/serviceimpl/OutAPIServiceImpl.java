package com.tang.billing.demoprovider.serviceimpl;

import com.tang.param.billing.Audit;
import com.tang.param.billing.Iris;
import com.tang.param.billing.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tang.api.billing.OutAPIService;
import com.tang.api.profile.HelloServiceProf;

import java.util.List;

@Service("HelloService")
public class OutAPIServiceImpl implements OutAPIService {

    private Logger logger = LoggerFactory.getLogger(OutAPIServiceImpl.class);

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

    @Override
    public List<Model> ModelList() {
        return helloServiceProf.ModelList();
    }

    @Override
    public List<Audit> auditGLM(List<Audit> param) {
        return helloServiceProf.auditGLM(param);
    }

    @Override
    public List<Iris> irisHcluster(List<Iris> param) {
        return helloServiceProf.irisHcluster(param);
    }

    @Override
    public List<Iris> irisRandomForest(List<Iris> param) {
        return helloServiceProf.irisRandomForest(param);
    }

    @Override
    public List<Iris> irisNnet(List<Iris> param) {
        return helloServiceProf.irisNnet(param);
    }

    @Override
    public List<Iris> irisSVM(List<Iris> param) {
        return helloServiceProf.irisSVM(param);
    }


}
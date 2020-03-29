package com.tang.billing.demoprovider.serviceimpl;

import com.tang.base.util.ValidateUtil;
import com.tang.param.billing.*;
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

    static String defaultRequestDesc1 = "request dataSet is empty, default examples have benn shown";
    static String defaultRequestDesc2 = "request dataSet acquired, prediction has been done";
    static String defaultModelDesc1 = "Classification and Regression Trees — rpart";
    static String defaultModelDesc2 = "Regression Models — glm from stats";
    static String defaultModelDesc3 = "Clustering Models — hclust";
    static String defaultModelDesc4 = "Random Forest (and randomSurvivalForest)— randomForest";
    static String defaultModelDesc5 = "Neural Networks — nnet";
    static String defaultModelDesc6 = "Support Vector Machines — kernlab";
    static String defaultDataIris = "    //Sepal.Length Sepal.Width Petal.Length Petal.Width    Species\n" +
            "    //4.5         2.3          1.3         0.3     setosa\n" +
            "    //6.0         2.2          4.0         1.0 versicolor\n" +
            "    //5.8         2.7          5.1         1.9  virginica";
    static String defaultDataAudit = "//    Age Employment Education       Income       TARGET_Adjusted\n" +
            "//    60    Private   College      7568.23           1\n" +
            "//    23    Private      Yr11      260405.44         0";

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
                        "info      :" + name + "\n" +
                        "========================================================";
        logger.info(showInfo);
        return showInfo;
    }

    @Override
    public List<Model> ModelList() {
        return helloServiceProf.ModelList();
    }

    @Override
    public IrisSet irisTree(List<Iris> param) {
        IrisSet ret = new IrisSet();
        if (!ValidateUtil.validateNotEmpty(param)) {
            ret.setRequestDesc(defaultRequestDesc1);
            ret.setModelDesc(defaultModelDesc1);
            ret.setDefaultData(defaultDataIris);
        } else {
            ret.setRequestDesc(defaultRequestDesc2);
            ret.setModelDesc(defaultModelDesc1);
        }
        ret.setIrisList(helloServiceProf.irisTree(param));
        return ret;
    }

    @Override
    public AuditSet auditGLM(List<Audit> param) {
        AuditSet ret = new AuditSet();
        if (!ValidateUtil.validateNotEmpty(param)) {
            ret.setRequestDesc(defaultRequestDesc1);
            ret.setModelDesc(defaultModelDesc2);
            ret.setDefaultData(defaultDataAudit);
        } else {
            ret.setRequestDesc(defaultRequestDesc2);
            ret.setModelDesc(defaultDataAudit);
        }
        ret.setAuditList(helloServiceProf.auditGLM(param));
        return ret;
    }

    @Override
    public IrisSet irisHcluster(List<Iris> param) {
        IrisSet ret = new IrisSet();
        if (!ValidateUtil.validateNotEmpty(param)) {
            ret.setRequestDesc(defaultRequestDesc1);
            ret.setModelDesc(defaultModelDesc3);
            ret.setDefaultData(defaultDataIris);
        } else {
            ret.setRequestDesc(defaultRequestDesc2);
            ret.setModelDesc(defaultModelDesc3);
        }
        ret.setIrisList(helloServiceProf.irisHcluster(param));
        return ret;
    }

    @Override
    public IrisSet irisRandomForest(List<Iris> param) {
        IrisSet ret = new IrisSet();
        if (!ValidateUtil.validateNotEmpty(param)) {
            ret.setRequestDesc(defaultRequestDesc1);
            ret.setModelDesc(defaultModelDesc4);
            ret.setDefaultData(defaultDataIris);
        } else {
            ret.setRequestDesc(defaultRequestDesc2);
            ret.setModelDesc(defaultModelDesc4);
        }
        ret.setIrisList(helloServiceProf.irisRandomForest(param));
        return ret;
    }

    @Override
    public IrisSet irisNnet(List<Iris> param) {
        IrisSet ret = new IrisSet();
        if (!ValidateUtil.validateNotEmpty(param)) {
            ret.setRequestDesc(defaultRequestDesc1);
            ret.setModelDesc(defaultModelDesc5);
            ret.setDefaultData(defaultDataIris);
        } else {
            ret.setRequestDesc(defaultRequestDesc2);
            ret.setModelDesc(defaultModelDesc5);
        }
        ret.setIrisList(helloServiceProf.irisNnet(param));
        return ret;
    }

    @Override
    public IrisSet irisSVM(List<Iris> param) {
        IrisSet ret = new IrisSet();
        if (!ValidateUtil.validateNotEmpty(param)) {
            ret.setRequestDesc(defaultRequestDesc1);
            ret.setModelDesc(defaultModelDesc6);
            ret.setDefaultData(defaultDataIris);
        } else {
            ret.setRequestDesc(defaultRequestDesc2);
            ret.setModelDesc(defaultModelDesc6);
        }
        ret.setIrisList(helloServiceProf.irisSVM(param));
        return ret;
    }


}
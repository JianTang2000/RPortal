package com.tang.api.billing;

import com.tang.param.billing.*;

import java.util.List;

public interface OutAPIService {

    String sayHello(String name);

    IrisSet irisTree(List<Iris> param);

    List<Model> ModelList();

    AuditSet auditGLM(List<Audit> param);

    IrisSet irisHcluster(List<Iris> param);

    IrisSet irisRandomForest(List<Iris> param);

    IrisSet irisNnet(List<Iris> param);

    IrisSet irisSVM(List<Iris> param);


}
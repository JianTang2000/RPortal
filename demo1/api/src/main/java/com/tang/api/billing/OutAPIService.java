package com.tang.api.billing;

import com.tang.param.billing.Audit;
import com.tang.param.billing.Iris;
import com.tang.param.billing.Model;

import java.util.List;

public interface OutAPIService {

    String sayHello(String name);

    List<Iris> irisTree(List<Iris> param);

    List<Model> ModelList();

    List<Audit> auditGLM(List<Audit> param);

    List<Iris> irisHcluster(List<Iris> param);

    List<Iris> irisRandomForest(List<Iris> param);

    List<Iris> irisNnet(List<Iris> param);

    List<Iris> irisSVM(List<Iris> param);


}
package com.tang.api.profile;

import com.tang.param.billing.Audit;
import com.tang.param.billing.Iris;
import com.tang.param.billing.Model;

import java.util.List;

public interface HelloServiceProf {

    String sayHello(String name);

    List<Model> ModelList();

    List<Iris> irisTree(List<Iris> param);

    List<Audit> auditGLM(List<Audit> param);

    List<Iris> irisHcluster(List<Iris> param);

    List<Iris> irisRandomForest(List<Iris> param);

    List<Iris> irisNnet(List<Iris> param);

    List<Iris> irisSVM(List<Iris> param);



}
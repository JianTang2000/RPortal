package com.tang.api.profile;

import com.tang.param.billing.Audit;
import com.tang.param.billing.Iris;
import com.tang.param.billing.Model;

import java.util.List;

public interface HelloServiceProf {

    /**
     * just show the in action service and its center
     * @param name could be null
     * @return the in action service and its center
     */
    String sayHello(String name);

    /**
     * show all available models
     * @return  all available models
     */
    List<Model> ModelList();

    /**
     * run tree and return predicted results
     * @return  predicted results
     */
    List<Iris> irisTree(List<Iris> param);

    List<Audit> auditGLM(List<Audit> param);

    List<Iris> irisHcluster(List<Iris> param);

    List<Iris> irisRandomForest(List<Iris> param);

    List<Iris> irisNnet(List<Iris> param);

    List<Iris> irisSVM(List<Iris> param);



}
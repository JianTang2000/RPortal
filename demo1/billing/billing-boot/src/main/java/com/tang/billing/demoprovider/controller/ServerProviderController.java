package com.tang.billing.demoprovider.controller;

import java.util.List;
import java.util.Map;

import com.tang.api.billing.OutAPIService;
import com.tang.param.billing.Audit;
import com.tang.param.billing.Iris;
import com.tang.param.billing.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tang.base.util.Json;
import com.tang.base.util.ValidateUtil;

@RestController
public class ServerProviderController {

    private Logger logger = LoggerFactory.getLogger(ServerProviderController.class);

    @Autowired
    OutAPIService outAPIService;

    @PutMapping("RpartTree/iris")
    public Map<String, Object> irisTree(List<Iris> param) {
        List<Iris> ret = outAPIService.irisTree(param);
        if (ValidateUtil.validateNotEmpty(ret)) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PutMapping("GLM/audit")
    public Map<String, Object> auditGLM(List<Audit> param) {
        List<Audit> ret = outAPIService.auditGLM(param);
        if (ValidateUtil.validateNotEmpty(ret)) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PutMapping("Hcluster/iris")
    public Map<String, Object> irisHcluster(List<Iris> param) {
        List<Iris> ret = outAPIService.irisHcluster(param);
        if (ValidateUtil.validateNotEmpty(ret)) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PutMapping("RandomForest/iris")
    public Map<String, Object> irisRandomForest(List<Iris> param) {
        List<Iris> ret = outAPIService.irisRandomForest(param);
        if (ValidateUtil.validateNotEmpty(ret)) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PutMapping("Nnet/iris")
    public Map<String, Object> irisNnet(List<Iris> param) {
        List<Iris> ret = outAPIService.irisNnet(param);
        if (ValidateUtil.validateNotEmpty(ret)) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PutMapping("SVM/iris")
    public Map<String, Object> irisSVM(List<Iris> param) {
        List<Iris> ret = outAPIService.irisSVM(param);
        if (ValidateUtil.validateNotEmpty(ret)) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    //return all existing model, and their API  detail
    @PutMapping("Model/List")
    public Map<String, Object> ModelList() {
        List<Model> ModelList = outAPIService.ModelList();
        if (ValidateUtil.validateNotEmpty(ModelList)) {
            return Json.success(ModelList);
        } else {
            return Json.fail(ModelList);
        }
    }

}

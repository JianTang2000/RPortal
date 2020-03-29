package com.tang.billing.demoprovider.controller;

import java.util.List;
import java.util.Map;

import com.tang.api.billing.OutAPIService;
import com.tang.base.util.BaseCommonUtil;
import com.tang.param.billing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.tang.base.util.Json;
import com.tang.base.util.ValidateUtil;

@RestController
public class ServerProviderController {

    private Logger logger = LoggerFactory.getLogger(ServerProviderController.class);

    @Autowired
    OutAPIService outAPIService;

    @PostMapping("RpartTree/iris")
    public Map<String, Object> irisTree(@RequestBody IrisSet param) {
        if (null == param) {
            IrisSet ret = outAPIService.irisTree(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        IrisSet ret = outAPIService.irisTree(param.getIrisList());
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PostMapping("GLM/audit")
    public Map<String, Object> auditGLM(@RequestBody AuditSet param) {
        if (null == param) {
            AuditSet ret = outAPIService.auditGLM(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        AuditSet ret = outAPIService.auditGLM(param.getAuditList());
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PostMapping("Hcluster/iris")
    public Map<String, Object> irisHcluster(@RequestBody IrisSet param) {
        if (null == param) {
            IrisSet ret = outAPIService.irisHcluster(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        IrisSet ret = outAPIService.irisHcluster(param.getIrisList());
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PostMapping("RandomForest/iris")
    public Map<String, Object> irisRandomForest(@RequestBody IrisSet param) {
        if (null == param) {
            IrisSet ret = outAPIService.irisRandomForest(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        IrisSet ret = outAPIService.irisRandomForest(param.getIrisList());
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PostMapping("Nnet/iris")
    public Map<String, Object> irisNnet(@RequestBody IrisSet param) {
        if (null == param) {
            IrisSet ret = outAPIService.irisNnet(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        IrisSet ret = outAPIService.irisNnet(param.getIrisList());
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    //get request is not suitable for large body(List<Iris>).so use post ,and more safe
//    @GetMapping("SVM/iris")
//    public Map<String, Object> irisSVM(IrisSet param) {
//        logger.info("irisSVM start, param is {}", BaseCommonUtil.objectToJsonString(param));
//        if (null == param) {
//            IrisSet ret = outAPIService.irisSVM(null);
//            if (ret != null) {
//                return Json.success(ret);
//            } else {
//                return Json.fail(ret);
//            }
//        }
//        IrisSet ret = outAPIService.irisSVM(param.getIrisList());
//        if (ret != null) {
//            return Json.success(ret);
//        } else {
//            return Json.fail(ret);
//        }
//    }


    // an jason body example
    //{
    //	"requestDesc":"test",
    //	"modelDesc":null,
    //	"defaultData":null,
    //	"irisList":[
    //		{"sepal_Width":2.3,"species":null,"sepal_Length":4.5,"petal_Length":1.3,"petal_Width":0.3},
    //		{"sepal_Width":3.2,"species":null,"sepal_Length":4.4,"petal_Length":1.3,"petal_Width":0.2}]}
    @PostMapping("SVM/iris")
    public Map<String, Object> saveActRuleBatch(@RequestBody IrisSet param) {
        logger.info("irisSVM 2 start, param is {}", BaseCommonUtil.objectToJsonString(param));
        if (null == param) {
            IrisSet ret = outAPIService.irisSVM(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        IrisSet ret = outAPIService.irisSVM(param.getIrisList());
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    //return all existing model, and their API  detail
    @GetMapping("Model/List")
    public Map<String, Object> ModelList() {
        List<Model> ModelList = outAPIService.ModelList();
        if (ValidateUtil.validateNotEmpty(ModelList)) {
            return Json.success(ModelList);
        } else {
            return Json.fail(ModelList);
        }
    }

//    @GetMapping("mainView/restfulTest0045")
//    public void test() {
//        logger.info("Run restful test");
//        logger.info("Run restful end");
//    }

}

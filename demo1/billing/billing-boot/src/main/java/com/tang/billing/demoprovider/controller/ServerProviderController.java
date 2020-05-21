package com.tang.billing.demoprovider.controller;

import java.util.ArrayList;
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

    @PutMapping("RpartTree/iris2")
    public Map<String, Object> irisTree2(Iris param2) {
        logger.info("RpartTree iris2 start, param is {}", BaseCommonUtil.objectToJsonString(param2));
        if (null == param2) {
            IrisSet ret = outAPIService.irisTree(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        List<Iris> param = new ArrayList<>();
        param.add(param2);
        IrisSet ret = outAPIService.irisTree(param);
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    @PutMapping("GLM/audit2")
    public Map<String, Object> auditGLM2(Audit param2) {
        logger.info("auditGLM2  start, param is {}", BaseCommonUtil.objectToJsonString(param2));
        if (null == param2) {
            AuditSet ret = outAPIService.auditGLM(null);
            if (ret != null) {
                return Json.success(ret);
            } else {
                return Json.fail(ret);
            }
        }
        List<Audit> param = new ArrayList<>();
        param.add(param2);
        AuditSet ret = outAPIService.auditGLM(param);
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }


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

    @GetMapping("RpartTree/iris")
    public Map<String, Object> irisTree() {
        IrisSet ret = outAPIService.irisTree(null);
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    /**
     * http://127.0.0.1:8080/GLM/audit
     * an example for jason
     * {
     * "requestDesc":"test",
     * "modelDesc":null,
     * "defaultData":null,
     * "auditList":[
     * {"age":74,"education":"HSgrad","income":33144.40,"employment":"Private","target_Adjusted":0.0},
     * {"age":42,"education":"College","income":66139.36,"employment":"PSState","target_Adjusted":0.0}]
     * }
     */
    @PostMapping("GLM/audit")
    public Map<String, Object> auditGLM(@RequestBody AuditSet param) {
        logger.info("auditGLM  start, param is {}", BaseCommonUtil.objectToJsonString(param));
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

    @GetMapping("GLM/audit")
    public Map<String, Object> auditGLM() {
        AuditSet ret = outAPIService.auditGLM(null);
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

    @GetMapping("Hcluster/iris")
    public Map<String, Object> irisHcluster() {
        IrisSet ret = outAPIService.irisHcluster(null);
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

    @GetMapping("RandomForest/iris")
    public Map<String, Object> irisRandomForest() {
        IrisSet ret = outAPIService.irisRandomForest(null);
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

    /**
     * get
     * http://127.0.0.1:8080/Nnet/iris
     */
    @GetMapping("Nnet/iris")
    public Map<String, Object> irisNnet() {
        IrisSet ret = outAPIService.irisNnet(null);
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    /**
     * post
     * http://127.0.0.1:8080/SVM/iris
     * //get request is not suitable for large body(List<Iris>).so use post ,and safer
     * // an jason body example (both setosa)
     * //{
     * //	"requestDesc":"test",
     * //	"modelDesc":null,
     * //	"defaultData":null,
     * //	"irisList":[
     * //		{"sepal_Width":2.3,"species":null,"sepal_Length":4.5,"petal_Length":1.3,"petal_Width":0.3},
     * //		{"sepal_Width":3.2,"species":null,"sepal_Length":4.4,"petal_Length":1.3,"petal_Width":0.2}]}
     */
    @PostMapping("SVM/iris")
    public Map<String, Object> irisSVM(@RequestBody IrisSet param) {
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

    /**
     * get
     * http://127.0.0.1:8080/SVM/iris
     */
    @GetMapping("SVM/iris")
    public Map<String, Object> irisSVM() {
        IrisSet ret = outAPIService.irisSVM(null);
        if (ret != null) {
            return Json.success(ret);
        } else {
            return Json.fail(ret);
        }
    }

    /**
     * get
     * return all existing model, and their API  detail
     * http://127.0.0.1:8080/Model/List
     */
    //
    @GetMapping("Model/List")
    public Map<String, Object> ModelList() {
        List<Model> ModelList = outAPIService.ModelList();
        if (ValidateUtil.validateNotEmpty(ModelList)) {
            return Json.success(ModelList);
        } else {
            return Json.fail(ModelList);
        }
    }


}

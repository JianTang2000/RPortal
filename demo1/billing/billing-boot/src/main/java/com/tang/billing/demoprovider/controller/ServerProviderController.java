package com.tang.billing.demoprovider.controller;

import java.util.List;
import java.util.Map;

import com.tang.api.billing.OutAPIService;
import com.tang.base.util.BaseCommonUtil;
import com.tang.param.billing.Iris;
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

    @PutMapping("randomForest/rpart")
    public Map<String, Object> findAllEffKey() {
        logger.info("start.");
        List<String> result = null;
        if (ValidateUtil.validateNotEmpty(result)) {
            return Json.success(result);
        }
        else {
            return Json.fail(result);
        }
    }


    //Sepal.Length Sepal.Width Petal.Length Petal.Width    Species
    // 5.1         3.5          1.4         0.2     setosa
    @PutMapping("irisTree/rpart")
    public Map<String, Object> irisTree(List<Iris> param) {
        logger.info("irisTree (based on rpart) start at {} (ms) ", System.currentTimeMillis());
        logger.info("request input: ", BaseCommonUtil.objectToJsonString(param));
        List<Iris> ret = outAPIService.irisTree(param);
        List<String> result = null;
        if (ValidateUtil.validateNotEmpty(result)) {
            logger.info("irisTree (based on rpart) end at {} (ms) ", System.currentTimeMillis());
            return Json.success(result);
        }
        else {
            logger.info("irisTree (based on rpart) end at {} (ms) ", System.currentTimeMillis());
            return Json.fail(result);
        }
    }

}

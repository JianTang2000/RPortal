package com.tang.billing.demoprovider.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tang.base.util.Json;
import com.tang.base.util.ValidateUtil;

@RestController
public class LocalCacheController {

    private Logger logger = LoggerFactory.getLogger(LocalCacheController.class);

    @PutMapping("localCache/getCacheInfo")
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

    @PutMapping("localCache/handleRefresh")
    public Map<String, Object> handleRefresh(List<String> param) {
        logger.info("start.");
        List<String> result = null;
        if (ValidateUtil.validateNotEmpty(result)) {
            return Json.success(result);
        }
        else {
            return Json.fail(result);
        }
    }

}

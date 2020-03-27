package com.tang.profile.demoprovider.serviceimpl;

import com.tang.base.util.BaseCommonUtil;
import com.tang.base.util.ValidateUtil;
import com.tang.param.billing.Iris;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;

import java.io.*;
import java.util.*;

import org.dmg.pmml.*;

import com.tang.api.profile.HelloServiceProf;

import java.util.List;

/**
 * < >
 */
@Service("HelloServiceProf")
public class HelloServiceProfImpl implements HelloServiceProf {

    static String IrisTreePath = System.getProperty("user.dir") + "\\profile\\profile-boot\\src\\main\\resources\\extraData\\irisTree.pmml";

    //4.5         2.3          1.3         0.3     setosa
    //6.0         2.2          4.0         1.0 versicolor
    //5.8         2.7          5.1         1.9  virginica
    private static Map<FieldName, Double> IrisSetosa = new HashMap<>();
    private static Map<FieldName, Double> IrisVersicolor = new HashMap<>();
    private static Map<FieldName, Double> IrisVirginica = new HashMap<>();

    static {
        IrisSetosa.put(new FieldName("Sepal.Length"), 4.5);
        IrisSetosa.put(new FieldName("Sepal.Width"), 2.3);
        IrisSetosa.put(new FieldName("Petal.Length"), 1.3);
        IrisSetosa.put(new FieldName("Petal.Width"), 0.3);
    }

    static {
        IrisVersicolor.put(new FieldName("Sepal.Length"), 6.0);
        IrisVersicolor.put(new FieldName("Sepal.Width"), 2.2);
        IrisVersicolor.put(new FieldName("Petal.Length"), 4.0);
        IrisVersicolor.put(new FieldName("Petal.Width"), 1.0);
    }

    static {
        IrisVirginica.put(new FieldName("Sepal.Length"), 5.8);
        IrisVirginica.put(new FieldName("Sepal.Width"), 2.7);
        IrisVirginica.put(new FieldName("Petal.Length"), 5.1);
        IrisVirginica.put(new FieldName("Petal.Width"), 1.9);
    }

    /**
     * < 日志 >
     */
    private Logger logger = LoggerFactory.getLogger(HelloServiceProfImpl.class);

    @Override
    public String sayHello(String name) {
        String showInfo =
                "========================================================\n" +
                        "center    :profile\n" +
                        "type      :Spring service\n" +
                        "package   :com.tang.profile.demoprovider.serviceimpl.HelloServiceProfImpl\n" +
                        "function  :sayHello\n" +
                        "desc      :show parameter only\n" +
                        "info      :" + name + "\n" +
                        "========================================================";
        logger.info(showInfo);
        return showInfo;
    }

    @Override
    public List<Iris> irisTree(List<Iris> param) {
        // null , run default dataset (examples)
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "desc    :iris TreeModel examples :\n" +
                    "========================================================");
            Map<FieldName, Double> result = (Map<FieldName, Double>) evaluateTree(IrisTreePath, IrisSetosa);
            logger.info(BaseCommonUtil.objectToJsonString(result));
            Map<FieldName, Double> result2 = (Map<FieldName, Double>) evaluateTree(IrisTreePath, IrisSetosa);
            logger.info(BaseCommonUtil.objectToJsonString(result2));
            Map<FieldName, Double> result3 = (Map<FieldName, Double>) evaluateTree(IrisTreePath, IrisSetosa);
            logger.info(BaseCommonUtil.objectToJsonString(result3));
            return null;
        }
        // not null, run input by using pmml
        for (Iris iris : param) {
            Map<FieldName, Double> input = new HashMap<>();
            input.put(new FieldName("Sepal.Length"), iris.getSepal_Length());
            input.put(new FieldName("Sepal.Width"), iris.getSepal_Width());
            input.put(new FieldName("Petal.Length"), iris.getPetal_Length());
            input.put(new FieldName("Petal.Width"), iris.getPetal_Width());
            Map<FieldName, ?> result =  evaluateTree(IrisTreePath, input);
            logger.info(BaseCommonUtil.objectToJsonString(result));
        }
        return null;
    }


    private static Map<FieldName, ?> evaluateTree(String modelPath, Map<FieldName, ?> map) {
        System.out.println(modelPath);
        PMML model = null;
        try {
            model = org.dmg.pmml.IOUtil.unmarshal(new File(modelPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
        TreeModelEvaluator tme = new TreeModelEvaluator(model);
        Map<FieldName, ?> result = tme.evaluate(map);
        return result;
    }

    public static void main(String[] args) {
        evaluateTree(IrisTreePath, null);
    }
}
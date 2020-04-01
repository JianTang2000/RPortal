package com.tang.profile.demoprovider.serviceimpl;

import com.tang.base.util.ValidateUtil;
import com.tang.param.billing.Audit;
import com.tang.param.billing.Iris;
import com.tang.param.billing.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;
import org.dmg.pmml.FieldName;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tang.api.profile.HelloServiceProf;

@Service("HelloServiceProf")
public class HelloServiceProfImpl implements HelloServiceProf {

    static String dir = System.getProperty("user.dir");
    static String Url = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    static String pmml_path = System.getenv("pmml_path");

//    static String IrisTreePath = Url + "extraData\\irisRpart.pmml";
//    static String AuditGLMPath = Url + "extraData\\auditGLM.pmml";
//    static String IrisHclustPath = Url + "extraData\\irisHclust.pmml";
//    static String IrisRandomForestPath = Url + "extraData\\irisRandomForest.pmml";
//    static String IrisNnetPath = Url + "extraData\\irisNnet.pmml";
//    static String IrisSVMPath = Url + "extraData\\irisSVM.pmml";

    private static FieldName Predicted_Species = new FieldName("Predicted_Species");
    private static FieldName predictedValue = new FieldName("predictedValue");
    private static FieldName Predicted_Adjusted = new FieldName("Predicted_TARGET_Adjusted");

    // Sepal.Length Sepal.Width Petal.Length Petal.Width    Species
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

//    Age Employment Education       Income       TARGET_Adjusted
//    60    Private   College      7568.23           1
//    23    Private      Yr11      260405.44         0

    private static Map<FieldName, Object> AuditT1 = new HashMap<>();
    private static Map<FieldName, Object> AuditT0 = new HashMap<>();

    static {
        AuditT1.put(new FieldName("Age"), 60);
        AuditT1.put(new FieldName("Employment"), "Private");
        AuditT1.put(new FieldName("Education"), "College");
        AuditT1.put(new FieldName("Income"), 7568.23);
    }

    static {
        AuditT0.put(new FieldName("Age"), 23);
        AuditT0.put(new FieldName("Employment"), "Private");
        AuditT0.put(new FieldName("Education"), "Yr11");
        AuditT0.put(new FieldName("Income"), 260405.44);
    }


    private static PMML pmmlTree = new PMML();
    private static PMML pmmlGLM = new PMML();
    private static PMML pmmlHcluster = new PMML();
    private static PMML pmmlRS = new PMML();
    private static PMML pmmlSVM = new PMML();
    private static PMML pmmlNet = new PMML();

    static {
        InputStream inputStream = null;
        if (pmml_path != null) {
            try {
                inputStream = new FileInputStream(pmml_path + "\\extraData\\irisRpart.pmml");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\extraData\\irisRpart.pmml");
        }
        if (inputStream == null) {
            new Exception();
        }
        InputStream is = inputStream;
        try {
            pmmlTree = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (javax.xml.bind.JAXBException e2) {
            e2.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        InputStream inputStream = null;
        if (pmml_path != null) {
            try {
                inputStream = new FileInputStream(pmml_path + "\\extraData\\auditGLM.pmml");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\extraData\\auditGLM.pmml");
        }
        if (inputStream == null) {
            new Exception();
        }
        InputStream is = inputStream;
        try {
            pmmlGLM = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (javax.xml.bind.JAXBException e2) {
            e2.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        InputStream inputStream = null;
        if (pmml_path != null) {
            try {
                inputStream = new FileInputStream(pmml_path + "\\extraData\\irisHclust.pmml");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\extraData\\irisHclust.pmml");
        }
        if (inputStream == null) {
            new Exception();
        }
        InputStream is = inputStream;
        try {
            pmmlHcluster = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (javax.xml.bind.JAXBException e2) {
            e2.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        InputStream inputStream = null;
        if (pmml_path != null) {
            try {
                inputStream = new FileInputStream(pmml_path + "\\extraData\\irisRandomForest.pmml");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\extraData\\irisRandomForest.pmml");
        }
        if (inputStream == null) {
            new Exception();
        }
        InputStream is = inputStream;
        try {
            pmmlRS = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (javax.xml.bind.JAXBException e2) {
            e2.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        InputStream inputStream = null;
        if (pmml_path != null) {
            try {
                inputStream = new FileInputStream(pmml_path + "\\extraData\\irisSVM.pmml");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\extraData\\irisSVM.pmml");
        }
        if (inputStream == null) {
            new Exception();
        }
        InputStream is = inputStream;
        try {
            pmmlSVM = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (javax.xml.bind.JAXBException e2) {
            e2.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    static {
        InputStream inputStream = null;
        if (pmml_path != null) {
            try {
                inputStream = new FileInputStream(pmml_path + "\\extraData\\irisNnet.pmml");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("\\extraData\\irisNnet.pmml");
        }
        if (inputStream == null) {
            new Exception();
        }
        InputStream is = inputStream;
        try {
            pmmlNet = org.jpmml.model.PMMLUtil.unmarshal(is);
        } catch (org.xml.sax.SAXException e1) {
            e1.printStackTrace();
        } catch (javax.xml.bind.JAXBException e2) {
            e2.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private Logger logger = LoggerFactory.getLogger(HelloServiceProfImpl.class);

    @Override
    public String sayHello(String name) {
        String showInfo =
                "========================================================\n" +
                        "center    :profile\n" +
                        "type      :Spring service\n" +
                        "package   :com.tang.profile.demoprovider.serviceimpl.HelloServiceProfImpl\n" +
                        "function  :sayHello\n" +
                        "desc      :show service & center\n" +
                        "info      :" + name + "\n" +
                        "========================================================";
        logger.info(showInfo);
        return showInfo;
    }

    @Override
    public List<Model> ModelList() {
//        logger.info("========================================================\n" +
//                "return all models :\n" +
//                "========================================================");
        List<Model> ret = new ArrayList<>();
        Model model1 = new Model();
        model1.setModelDesc("Classification and Regression Trees — rpart");
        model1.setFormula("tree.model = rpart(Species ~ ., data=iris)");
        model1.setURL("XXX/irisRpart");
        model1.setParams("     double Sepal_Length;\n" +
                "     double Sepal_Width;\n" +
                "     double Petal_Length;\n" +
                "     double Petal_Width;");
        Model model2 = new Model();
        model2.setModelDesc("Regression Models — glm from stats");
        model2.setFormula("mod <- glm(Adjusted ~ Age + Employment + Education + Income, data = audit, family = binomial(logit))");
        model2.setURL("XXX/auditGLM");
        model2.setParams("     int Age;\n" +
                "     String Employment;\n" +
                "     String Education;\n" +
                "     double Income;");
        Model model3 = new Model();
        model3.setModelDesc("Clustering Models — hclust");
        model3.setFormula("model <- hclusterpar(iris[, -5])");
        model3.setURL("XXX/irisHclust");
        model3.setParams("     double Sepal_Length;\n" +
                "     double Sepal_Width;\n" +
                "     double Petal_Length;\n" +
                "     double Petal_Width;");
        model3.setSpecialInfo(" <Cluster name=\"1\">\n" +
                "   <Array n=\"4\" type=\"real\">5.006 3.428 1.462 0.246</Array>\n" +
                "  </Cluster>\n" +
                "  <Cluster name=\"2\">\n" +
                "   <Array n=\"4\" type=\"real\">6.54583333333333 2.96388888888889 5.27361111111111 1.85</Array>\n" +
                "  </Cluster>\n" +
                "  <Cluster name=\"3\">\n" +
                "   <Array n=\"4\" type=\"real\">5.53214285714286 2.63571428571429 3.96071428571429 1.22857142857143</Array>\n" +
                "  </Cluster>");
        Model model4 = new Model();
        model4.setModelDesc("Random Forest (and randomSurvivalForest)— randomForest");
        model4.setFormula("iris_rf <- randomForest(Species ~ ., data = iris, ntree = 20)");
        model4.setURL("XXX/irisRandomForest");
        model4.setParams("     double Sepal_Length;\n" +
                "     double Sepal_Width;\n" +
                "     double Petal_Length;\n" +
                "     double Petal_Width;");
        Model model5 = new Model();
        model5.setModelDesc("Neural Networks — nnet");
        model5.setFormula("fit <- nnet(Species ~ ., data = iris, size = 4)");
        model5.setURL("XXX/irisNnet");
        model5.setParams("     double Sepal_Length;\n" +
                "     double Sepal_Width;\n" +
                "     double Petal_Length;\n" +
                "     double Petal_Width;");
        Model model6 = new Model();
        model6.setModelDesc("Support Vector Machines — kernlab");
        model6.setFormula("model <- ksvm(Species ~ ., data = iris)");
        model6.setURL("XXX/irisSVM");
        model6.setParams("     double Sepal_Length;\n" +
                "     double Sepal_Width;\n" +
                "     double Petal_Length;\n" +
                "     double Petal_Width;");
        ret.add(model1);
        ret.add(model2);
        ret.add(model3);
        ret.add(model4);
        ret.add(model5);
        ret.add(model6);
        return ret;
    }

    @Override
    public List<Iris> irisTree(List<Iris> param) {
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmmlTree);
        // null , run default dataset (examples)
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "input param is null, show default examples for irisTree :\n" +
                    "========================================================");
            List<Iris> defaultIris = new ArrayList<>();
            Iris iris1 = new Iris();
            iris1.setSepal_Length(4.5);
            iris1.setSepal_Width(2.3);
            iris1.setPetal_Length(1.3);
            iris1.setPetal_Width(0.3);
            String SP1 = (String) evaluator.evaluate(IrisSetosa).get(Predicted_Species);
            iris1.setSpecies(SP1);
            Iris iris2 = new Iris();
            iris2.setSepal_Length(6.0);
            iris2.setSepal_Width(2.2);
            iris2.setPetal_Length(4.0);
            iris2.setPetal_Width(1.0);
            String SP2 = (String) evaluator.evaluate(IrisVersicolor).get(Predicted_Species);
            iris2.setSpecies(SP2);
            Iris iris3 = new Iris();
            iris3.setSepal_Length(5.8);
            iris3.setSepal_Width(2.7);
            iris3.setPetal_Length(5.1);
            iris3.setPetal_Width(1.9);
//            Map<FieldName, ?> result = evaluator.evaluate(IrisVirginica);
//            String SP3 = (String) result.get(Predicted_Species);
            String SP3 = (String) evaluator.evaluate(IrisVirginica).get(Predicted_Species);
            iris3.setSpecies(SP3);
            defaultIris.add(iris1);
            defaultIris.add(iris2);
            defaultIris.add(iris3);
            return defaultIris;
        } else {
            // not null, run input by using pmml
            for (Iris iris : param) {
                Map<FieldName, Double> input = new HashMap<>();
                input.put(new FieldName("Sepal.Length"), iris.getSepal_Length());
                input.put(new FieldName("Sepal.Width"), iris.getSepal_Width());
                input.put(new FieldName("Petal.Length"), iris.getPetal_Length());
                input.put(new FieldName("Petal.Width"), iris.getPetal_Width());
                String SP = (String) evaluator.evaluate(input).get(Predicted_Species);
                iris.setSpecies(SP);
            }
            return param;
        }
    }


    @Override
    public List<Audit> auditGLM(List<Audit> param) {
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmmlGLM);
        // null , run default dataset (examples)
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "input param is null, show default examples for audit GLM :\n" +
                    "========================================================");
            List<Audit> defaultAudit = new ArrayList<>();
            Audit audit1 = new Audit();
            audit1.setAge(60);
            audit1.setEducation("College");
            audit1.setEmployment("Private");
            audit1.setIncome(7568.23);
            Object SP1 = evaluator.evaluate(AuditT1).get(Predicted_Adjusted);
//            Map<FieldName, ?> result = evaluator.evaluate(AuditT1);
//            Object SP1 = result.get(Predicted_Adjusted);
            audit1.setTARGET_Adjusted((Double) SP1);
            Audit audit2 = new Audit();
            audit2.setAge(23);
            audit2.setEducation("Yr11");
            audit2.setEmployment("Private");
            audit2.setIncome(260405.44);
            Object SP2 = evaluator.evaluate(AuditT0).get(Predicted_Adjusted);
            audit2.setTARGET_Adjusted((Double) SP2);
            defaultAudit.add(audit1);
            defaultAudit.add(audit2);
            return defaultAudit;
        } else {
            // not null, run input by using pmml
            for (Audit audit : param) {
                Map<FieldName, Object> input = new HashMap<>();
                input.put(new FieldName("Age"), audit.getAge());
                input.put(new FieldName("Employment"), audit.getEmployment());
                input.put(new FieldName("Education"), audit.getEducation());
                input.put(new FieldName("Income"), audit.getIncome());
                Object SP = evaluator.evaluate(input).get(Predicted_Adjusted);
                audit.setTARGET_Adjusted((Double) SP);
            }
            return param;
        }
    }

    @Override
    public List<Iris> irisHcluster(List<Iris> param) {
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmmlHcluster);
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "input param is null, show default examples for iris Hcluster :\n" +
                    "========================================================");
            List<Iris> defaultIris = new ArrayList<>();
            Iris iris1 = new Iris();
            iris1.setSepal_Length(4.5);
            iris1.setSepal_Width(2.3);
            iris1.setPetal_Length(1.3);
            iris1.setPetal_Width(0.3);
            String SP1 = "cluster:" + evaluator.evaluate(IrisSetosa).get(predictedValue);
            iris1.setSpecies(SP1);
            Iris iris2 = new Iris();
            iris2.setSepal_Length(6.0);
            iris2.setSepal_Width(2.2);
            iris2.setPetal_Length(4.0);
            iris2.setPetal_Width(1.0);
            String SP2 = "cluster:" + evaluator.evaluate(IrisVersicolor).get(predictedValue);
            iris2.setSpecies(SP2);
            Iris iris3 = new Iris();
            iris3.setSepal_Length(5.8);
            iris3.setSepal_Width(2.7);
            iris3.setPetal_Length(5.1);
            iris3.setPetal_Width(1.9);
//            Map<FieldName, ?> result = evaluator.evaluate(IrisVirginica);
//            String SP3 = (String) result.get(Predicted_Species);
            String SP3 = "cluster:" + evaluator.evaluate(IrisVirginica).get(predictedValue);
            iris3.setSpecies(SP3);
            defaultIris.add(iris1);
            defaultIris.add(iris2);
            defaultIris.add(iris3);
            return defaultIris;
        } else {
            // not null, run input by using pmml
            for (Iris iris : param) {
                Map<FieldName, Double> input = new HashMap<>();
                input.put(new FieldName("Sepal.Length"), iris.getSepal_Length());
                input.put(new FieldName("Sepal.Width"), iris.getSepal_Width());
                input.put(new FieldName("Petal.Length"), iris.getPetal_Length());
                input.put(new FieldName("Petal.Width"), iris.getPetal_Width());
                String SP = "cluster:" + evaluator.evaluate(input).get(predictedValue);
                iris.setSpecies(SP);
            }
            return param;
        }
    }

    @Override
    public List<Iris> irisRandomForest(List<Iris> param) {
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmmlRS);
        // null , run default dataset (examples)
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "input param is null, show default examples for irisRandomForest :\n" +
                    "========================================================");
            List<Iris> defaultIris = new ArrayList<>();
            Iris iris1 = new Iris();
            iris1.setSepal_Length(4.5);
            iris1.setSepal_Width(2.3);
            iris1.setPetal_Length(1.3);
            iris1.setPetal_Width(0.3);
            String SP1 = (String) evaluator.evaluate(IrisSetosa).get(Predicted_Species);
            iris1.setSpecies(SP1);
            Iris iris2 = new Iris();
            iris2.setSepal_Length(6.0);
            iris2.setSepal_Width(2.2);
            iris2.setPetal_Length(4.0);
            iris2.setPetal_Width(1.0);
            String SP2 = (String) evaluator.evaluate(IrisVersicolor).get(Predicted_Species);
            iris2.setSpecies(SP2);
            Iris iris3 = new Iris();
            iris3.setSepal_Length(5.8);
            iris3.setSepal_Width(2.7);
            iris3.setPetal_Length(5.1);
            iris3.setPetal_Width(1.9);
//            Map<FieldName, ?> result = evaluator.evaluate(IrisVirginica);
//            String SP3 = (String) result.get(Predicted_Species);
            String SP3 = (String) evaluator.evaluate(IrisVirginica).get(Predicted_Species);
            iris3.setSpecies(SP3);
            defaultIris.add(iris1);
            defaultIris.add(iris2);
            defaultIris.add(iris3);
            return defaultIris;
        } else {
            // not null, run input by using pmml
            for (Iris iris : param) {
                Map<FieldName, Double> input = new HashMap<>();
                input.put(new FieldName("Sepal.Length"), iris.getSepal_Length());
                input.put(new FieldName("Sepal.Width"), iris.getSepal_Width());
                input.put(new FieldName("Petal.Length"), iris.getPetal_Length());
                input.put(new FieldName("Petal.Width"), iris.getPetal_Width());
                String SP = (String) evaluator.evaluate(input).get(Predicted_Species);
                iris.setSpecies(SP);
            }
            return param;
        }
    }

    @Override
    public List<Iris> irisNnet(List<Iris> param) {
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmmlNet);
        // null , run default dataset (examples)
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "input param is null, show default examples for iris nnet :\n" +
                    "========================================================");
            List<Iris> defaultIris = new ArrayList<>();
            Iris iris1 = new Iris();
            iris1.setSepal_Length(4.5);
            iris1.setSepal_Width(2.3);
            iris1.setPetal_Length(1.3);
            iris1.setPetal_Width(0.3);
            String SP1 = (String) evaluator.evaluate(IrisSetosa).get(Predicted_Species);
            iris1.setSpecies(SP1);
            Iris iris2 = new Iris();
            iris2.setSepal_Length(6.0);
            iris2.setSepal_Width(2.2);
            iris2.setPetal_Length(4.0);
            iris2.setPetal_Width(1.0);
            String SP2 = (String) evaluator.evaluate(IrisVersicolor).get(Predicted_Species);
            iris2.setSpecies(SP2);
            Iris iris3 = new Iris();
            iris3.setSepal_Length(5.8);
            iris3.setSepal_Width(2.7);
            iris3.setPetal_Length(5.1);
            iris3.setPetal_Width(1.9);
//            Map<FieldName, ?> result = evaluator.evaluate(IrisVirginica);
//            String SP3 = (String) result.get(Predicted_Species);
            String SP3 = (String) evaluator.evaluate(IrisVirginica).get(Predicted_Species);
            iris3.setSpecies(SP3);
            defaultIris.add(iris1);
            defaultIris.add(iris2);
            defaultIris.add(iris3);
            return defaultIris;
        } else {
            // not null, run input by using pmml
            for (Iris iris : param) {
                Map<FieldName, Double> input = new HashMap<>();
                input.put(new FieldName("Sepal.Length"), iris.getSepal_Length());
                input.put(new FieldName("Sepal.Width"), iris.getSepal_Width());
                input.put(new FieldName("Petal.Length"), iris.getPetal_Length());
                input.put(new FieldName("Petal.Width"), iris.getPetal_Width());
                String SP = (String) evaluator.evaluate(input).get(Predicted_Species);
                iris.setSpecies(SP);
            }
            return param;
        }
    }

    @Override
    public List<Iris> irisSVM(List<Iris> param) {
        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmmlSVM);
        // null , run default dataset (examples)
        if (!ValidateUtil.validateNotEmpty(param)) {
            logger.info("========================================================\n" +
                    "input param is null, show default examples for iris SVM :\n" +
                    "========================================================");
            List<Iris> defaultIris = new ArrayList<>();
            Iris iris1 = new Iris();
            iris1.setSepal_Length(4.5);
            iris1.setSepal_Width(2.3);
            iris1.setPetal_Length(1.3);
            iris1.setPetal_Width(0.3);
            String SP1 = (String) evaluator.evaluate(IrisSetosa).get(Predicted_Species);
            iris1.setSpecies(SP1);
            Iris iris2 = new Iris();
            iris2.setSepal_Length(6.0);
            iris2.setSepal_Width(2.2);
            iris2.setPetal_Length(4.0);
            iris2.setPetal_Width(1.0);
            String SP2 = (String) evaluator.evaluate(IrisVersicolor).get(Predicted_Species);
            iris2.setSpecies(SP2);
            Iris iris3 = new Iris();
            iris3.setSepal_Length(5.8);
            iris3.setSepal_Width(2.7);
            iris3.setPetal_Length(5.1);
            iris3.setPetal_Width(1.9);
//            Map<FieldName, ?> result = evaluator.evaluate(IrisVirginica);
//            String SP3 = (String) result.get(Predicted_Species);
            String SP3 = (String) evaluator.evaluate(IrisVirginica).get(Predicted_Species);
            iris3.setSpecies(SP3);
            defaultIris.add(iris1);
            defaultIris.add(iris2);
            defaultIris.add(iris3);
            return defaultIris;
        } else {
            // not null, run input by using pmml
            for (Iris iris : param) {
                Map<FieldName, Double> input = new HashMap<>();
                input.put(new FieldName("Sepal.Length"), iris.getSepal_Length());
                input.put(new FieldName("Sepal.Width"), iris.getSepal_Width());
                input.put(new FieldName("Petal.Length"), iris.getPetal_Length());
                input.put(new FieldName("Petal.Width"), iris.getPetal_Width());
                String SP = (String) evaluator.evaluate(input).get(Predicted_Species);
                iris.setSpecies(SP);
            }
            return param;
        }
    }

//    private static List<Iris> getIrises(List<Audit> param, PMML pmml) {
//        ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
//        Evaluator evaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
//        // null , run default dataset (examples)
//        if (!ValidateUtil.validateNotEmpty(param)) {
//            List<Audit> defaultAudit = new ArrayList<>();
//            Audit audit1 = new Audit();
//            audit1.setAge(60);
//            audit1.setEducation("College");
//            audit1.setEmployment("Private");
//            audit1.setIncome(7568.23);
////            Object SP1 = evaluator.evaluate(AuditT0).get(Predicted_Species);
//            Map<FieldName, ?> result = evaluator.evaluate(AuditT1);
//            Object SP1 = result.get(Predicted_Adjusted);
//
//            audit1.setTARGET_Adjusted((Double) SP1);
//            Audit audit2 = new Audit();
//            audit2.setAge(23);
//            audit2.setEducation("Yr11");
//            audit2.setEmployment("Private");
//            audit2.setIncome(260405.44);
//
//            Object SP2 = evaluator.evaluate(AuditT0).get(Predicted_Adjusted);
//
//            audit2.setTARGET_Adjusted((Double) SP2);
//            defaultAudit.add(audit1);
//            defaultAudit.add(audit2);
//            return null;
//        } else {
//            // not null, run input by using pmml
//            for (Audit audit : param) {
//                Map<FieldName, Object> input = new HashMap<>();
//                input.put(new FieldName("Age"), audit.getAge());
//                input.put(new FieldName("Employment"), audit.getEmployment());
//                input.put(new FieldName("Education"), audit.getEducation());
//                input.put(new FieldName("Income"), audit.getIncome());
//                Object SP = evaluator.evaluate(input).get(Predicted_Adjusted);
//                audit.setTARGET_Adjusted((Double) SP);
//            }
//            return null;
//        }
//    }


    //    public static void main(String[] args) {
////        //5.4         3.9          1.3         0.4     setosa
////        List<Iris> rua = new ArrayList<>();
////        Iris iris1 = new Iris();
////        iris1.setSepal_Length(5.4);
////        iris1.setSepal_Width(3.9);
////        iris1.setPetal_Length(1.3);
////        iris1.setPetal_Width(0.4);
////        rua.add(iris1);
//////        List<Iris> ret = getIrises(rua,pmmlRS);
////        List<Iris> ret2 = getIrises(null,pmmlSVM);
////        int a = 123;
//
//        //    60    Private   College      7568.23           1
////    23    Private      Yr11      260405.44         0
//        List<Audit> auditList = new ArrayList<>();
//        Audit audit1 = new Audit();
//        audit1.setAge(60);
//        audit1.setEmployment("Private");
//        audit1.setEducation("College");
//        audit1.setIncome(7568.23);
//        auditList.add(audit1);
//                List<Iris> ret = getIrises(auditList,pmmlGLM);
////        List<Iris> ret2 = getIrises(null,pmmlGLM);
//    }
    public static void main(String[] args) {
        System.out.println(Url);
    }

}
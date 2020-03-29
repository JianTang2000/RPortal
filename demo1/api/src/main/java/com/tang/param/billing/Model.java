package com.tang.param.billing;

import java.io.Serializable;

/**
 * @author tang
 * @Description: TODO
 */
public class Model implements Serializable {

    private static final long serialVersionUID = -1780491730241163250L;
    private String modelDesc;
    private String formula;
    private String URL;
    private String params;
    private String specialInfo;

    public String getSpecialInfo() {
        return specialInfo;
    }

    public void setSpecialInfo(String specialInfo) {
        this.specialInfo = specialInfo;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }
}

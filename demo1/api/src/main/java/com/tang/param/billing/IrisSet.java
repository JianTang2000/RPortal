package com.tang.param.billing;

import java.io.Serializable;
import java.util.List;

/**
 * @author tang
 * @Description: TODO
 */
public class IrisSet implements Serializable {

    public IrisSet() {

    }
    private static final long serialVersionUID = -1780494730241163550L;

    private String requestDesc;

    private String modelDesc;

    private String defaultData;

    private List<Iris> irisList;

    public String getRequestDesc() {
        return requestDesc;
    }

    public void setRequestDesc(String requestDesc) {
        this.requestDesc = requestDesc;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getDefaultData() {
        return defaultData;
    }

    public void setDefaultData(String defaultData) {
        this.defaultData = defaultData;
    }

    public List<Iris> getIrisList() {
        return irisList;
    }

    public void setIrisList(List<Iris> irisList) {
        this.irisList = irisList;
    }
}

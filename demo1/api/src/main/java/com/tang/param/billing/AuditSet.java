package com.tang.param.billing;

import java.io.Serializable;
import java.util.List;

/**
 * @author tang
 * @Description: TODO
 */
public class AuditSet implements Serializable {

    private static final long serialVersionUID = -1780494730241163550L;

    private String requestDesc;

    private String modelDesc;

    private String defaultData;

    private List<Audit> auditList;

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

    public List<Audit> getAuditList() {
        return auditList;
    }

    public void setAuditList(List<Audit> auditList) {
        this.auditList = auditList;
    }
}

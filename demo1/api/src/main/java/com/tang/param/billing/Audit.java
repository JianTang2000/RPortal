package com.tang.param.billing;

import java.io.Serializable;

/**
 * @author tang
 * @Description: TODO
 */
public class Audit implements Serializable {
    private static final long serialVersionUID = -1780491730241163150L;
    //mod <- glm(Adjusted ~ Age + Employment + Education + Income, data = audit, family = binomial(logit))
    private int Age;
    private String Employment;
    private String Education;
    private double Income;
    private double TARGET_Adjusted;

    public double getTARGET_Adjusted() {
        return TARGET_Adjusted;
    }

    public void setTARGET_Adjusted(double TARGET_Adjusted) {
        this.TARGET_Adjusted = TARGET_Adjusted;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getEmployment() {
        return Employment;
    }

    public void setEmployment(String employment) {
        Employment = employment;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public double getIncome() {
        return Income;
    }

    public void setIncome(double income) {
        Income = income;
    }
}

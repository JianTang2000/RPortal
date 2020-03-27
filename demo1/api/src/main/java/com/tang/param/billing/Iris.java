package com.tang.param.billing;

/**
 * @author tang
 * @Description: TODO
 */
public class Iris {

    /**
     * serialVersionUID <br>
     */
    private static final long serialVersionUID = -1780491730241163550L;

    private String Species;
    private double Sepal_Length;
    private double Sepal_Width;
    private double Petal_Length;
    private double Petal_Width;

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public double getSepal_Length() {
        return Sepal_Length;
    }

    public void setSepal_Length(double sepal_Length) {
        Sepal_Length = sepal_Length;
    }

    public double getSepal_Width() {
        return Sepal_Width;
    }

    public void setSepal_Width(double sepal_Width) {
        Sepal_Width = sepal_Width;
    }

    public double getPetal_Length() {
        return Petal_Length;
    }

    public void setPetal_Length(double petal_Length) {
        Petal_Length = petal_Length;
    }

    public double getPetal_Width() {
        return Petal_Width;
    }

    public void setPetal_Width(double petal_Width) {
        Petal_Width = petal_Width;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CaseComponent;

/**
 *
 * @author ss6035
 */
public class PatientDescription implements CaseComponent{
    
    private String id;
    private int age;
    private double[] bts;
    private double[] control;
    private int[][] drugs; 
    private double[] drugVec;

    @Override
    public Attribute getIdAttribute() {
        return new Attribute("id", this.getClass());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double[] getBts() {
        return bts;
    }

    public void setBts(double[] bts) {
        this.bts = bts;
    }

    public double[] getControl() {
        return control;
    }

    public void setControl(double[] control) {
        this.control = control;
    }

    public int[][] getDrugs() {
        return drugs;
    }

    public void setDrugs(int[][] drugs) {
        this.drugs = drugs;
        drugVec = new double[drugs.length * drugs[0].length];
        for(int i=0; i<drugs.length;i++){
            for(int j=0; j<drugs[0].length; j++){
                int indx = (i*drugs[0].length)+j;
//                System.out.println("Index: "+indx);
                drugVec[(i*drugs[0].length)+j] = drugs[i][j];
            }
        }
    }

    public int[] getDrug01() {
        return drugs[0];
    }

    public void setDrug01(int[] drug01) {
        this.drugs[0] = drug01;
    }

    public int[] getDrug02() {
        return drugs[2];
    }

    public void setDrug02(int[] drug02) {
        this.drugs[2] = drug02;
    }

    public int[] getDrug03() {
        return drugs[3];
    }

    public void setDrug03(int[] drug03) {
        this.drugs[3] = drug03;
    }

    public int[] getDrug04() {
        return drugs[4];
    }

    public void setDrug04(int[] drug04) {
        this.drugs[4] = drug04;
    }

    public int[] getDrug05() {
        return drugs[5];
    }

    public void setDrug05(int[] drug05) {
        this.drugs[5] = drug05;
    }

    public int[] getDrug06() {
        return drugs[6];
    }

    public void setDrug06(int[] drug06) {
        this.drugs[6] = drug06;
    }

    public int[] getDrug07() {
        return drugs[7];
    }

    public void setDrug07(int[] drug07) {
        this.drugs[7] = drug07;
    }

    public int[] getDrug08() {
        return drugs[8];
    }

    public void setDrug08(int[] drug08) {
        this.drugs[8] = drug08;
    }

    public int[] getDrug09() {
        return drugs[9];
    }

    public void setDrug09(int[] drug09) {
        this.drugs[9] = drug09;
    }

    public int[] getDrug10() {
        return drugs[10];
    }

    public void setDrug10(int[] drug10) {
        this.drugs[10] = drug10;
    }

    public int[] getDrug11() {
        return drugs[11];
    }

    public void setDrug11(int[] drug11) {
        this.drugs[11] = drug11;
    }

    public void setDrugVec(double[] drugVec) {
        this.drugVec = drugVec;
    }

    public double[] getDrugVec() {
        return drugVec;
    }
    
}

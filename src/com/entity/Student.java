package com.entity;

public class Student {
    private int id;
    private String name;
    private String gender;
    private String clazz;
    private double math_score;
    private double java_score;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public double getMath_score() {
        return math_score;
    }

    public void setMath_score(double math_score) {
        this.math_score = math_score;
    }

    public double getJava_score() {
        return java_score;
    }

    public void setJava_score(double java_score) {
        this.java_score = java_score;
    }
}
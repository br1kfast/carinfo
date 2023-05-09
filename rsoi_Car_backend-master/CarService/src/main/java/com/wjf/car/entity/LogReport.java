package com.wjf.car.entity;

/**
 * @author JunFei
 * @data
 * @purpose
 */

import java.io.Serializable;

public class LogReport implements Serializable {


    private Integer id;


    private String name;


    private String methodType;


    private String path;

    public LogReport() {}

    public LogReport(Integer id, String name, String methodType, String path) {
        this.id = id;
        this.name = name;
        this.methodType = methodType;
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "LogReport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", methodType='" + methodType + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}

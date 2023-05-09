package com.wjf.report.entity;

/**
 * @author JunFei
 * @data
 * @purpose
 */

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "logReport")
public class LogReport implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String methodType;

    @Column(nullable = false)
    private String path;

    public LogReport() {}

    public LogReport(Integer id, String name, String methodType, String path) {
        this.id = id;
        this.name = name;
        this.methodType = methodType;
        this.path = path;
    }
}

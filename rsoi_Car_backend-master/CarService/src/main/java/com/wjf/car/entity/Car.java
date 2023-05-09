package com.wjf.car.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author
 * @date
 * @purpose
 */
@Entity
@Data //@Data注解的主要作用是提高代码的简洁，使用这个注解可以省去实体类中大量的get()、 set()、 toString()等方法
@Table(name = "car", indexes = {
        @Index(name = "idx_car_uid", columnList = "car_uid"),
})
public class Car implements Serializable {
    //id注解指的是主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "car_uid", nullable = false, length = 40, unique = true)
    private Integer carUid;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String power;

    @Enumerated(EnumType.STRING)
    private Type type;

    public Car() {
    }

    public Car(Integer carUid) {
        this.carUid = carUid;
    }

    public Car(Integer id, String brand, String model, String power, Type type) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.power = power;
        this.type = type;
    }

    public Car(String model) {
        this.model = model;
    }
}

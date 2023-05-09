package com.wjf.car.service;

import com.wjf.car.entity.Car;

import java.util.List;

/**
 * @author
 * @date
 * @purpose
 */
public interface CarService {

    List<Car> findAll();

    Car findById(int CarUid);

    List<Car> findByBrand(String brand);

    String createCar(Car car);

    void deleteCar(int CarUid);

    String update(Car car);
}

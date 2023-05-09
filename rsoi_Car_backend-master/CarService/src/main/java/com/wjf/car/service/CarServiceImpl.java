package com.wjf.car.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.wjf.car.entity.Car;
import com.wjf.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;

/**
 * @author
 * @date
 * @purpose
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<Car> findAll() {
        return carRepository.findAll();
    }

    @HystrixCommand
    @Override
    public Car findById(@PathVariable int carUid) {
        return carRepository.findByCarUid(carUid)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Car not found for carUid %s", carUid)));
    }

    @Override
    public List<Car> findByBrand(String brand) {
        return carRepository.findCarsByBrand(brand)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Cars not found for brand %s", brand)));
    }

    @Override
    public String createCar(@Valid @RequestBody Car car) {
        int carUid = (int) (Math.random() * 100);
        car.setCarUid(carUid);
        Car c = carRepository.save(car);
        if (c.getId() > 0) {
            return "success";
        } else {
            return "error";
        }
    }

    @Override
    public void deleteCar(@PathVariable int carUid) {
        carRepository.deleteById(carUid);
    }

    @Override
    public String update(@Valid @RequestBody Car car) {
        Car c = carRepository.save(car);
        if (c.getId() > 0) {
            return "success";
        } else {
            return "error";
        }
    }
}

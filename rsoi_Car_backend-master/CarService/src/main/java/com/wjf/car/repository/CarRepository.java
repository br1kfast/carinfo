package com.wjf.car.repository;

import com.wjf.car.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author
 * @date
 * @purpose
 */
//接口 继承JpaRepository<实体类类型，主键类型>
public interface CarRepository extends JpaRepository<Car,Integer> {

    Optional<Car> findByCarUid(int carUid);
    Optional<List<Car>> findCarsByBrand(String brand);
}

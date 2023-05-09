package com.wjf.car.web;

import com.wjf.car.entity.Car;
import com.wjf.car.entity.LogReport;
import com.wjf.car.service.CarService;
import com.wjf.car.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;


/**
 * @author JunFei
 * @data
 * @purpose
 */

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarService carService;

    @Qualifier("com.wjf.car.service.ReportService")
    @Autowired
    ReportService reportService; //自动装配ReportService本地代理实例


    @GetMapping("/findall")
    public List<Car> findAll(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);
        return carService.findAll();
    }

    @GetMapping("/{carUid}")
    public Car findById(@PathVariable int carUid,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);
        return carService.findById(carUid);
    }

    @GetMapping("/carBrand/{brand}")
    public List<Car> findByBrand(@PathVariable String brand,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);
        return carService.findByBrand(brand);
    }


    @PostMapping(value = "/create")
    public String createCar(@Valid @RequestBody Car car,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);
        return carService.createCar(car);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{carUid}")
    public void deleteCar(@PathVariable int carUid,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);
        carService.deleteCar(carUid);
    }


    @PutMapping("/update")
    public String update(@Valid @RequestBody Car car,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);
        return carService.update(car);
    }

}

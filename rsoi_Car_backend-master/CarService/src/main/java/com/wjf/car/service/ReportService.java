package com.wjf.car.service;

import com.wjf.car.entity.LogReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author JunFei
 * @data
 * @purpose
 */
//直接指定url?如果是集群怎么办？
@FeignClient(value = "report-service",fallback = ReportServiceFallBack.class)
public interface ReportService {
    @PostMapping("/reports/saveReport")
    void saveLogInfo(@Valid @RequestBody LogReport logReport);

}

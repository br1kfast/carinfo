package com.wjf.user.service;

import com.wjf.user.entity.LogReport;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

/**
 * @author JunFei
 * @data
 * @purpose
 */
@FeignClient(value = "report-service", fallback = ReportServiceFallBack.class)
public interface ReportService {


        @PostMapping("/reports/saveReport")
        void saveLogInfo(@Valid @RequestBody LogReport logReport);


}

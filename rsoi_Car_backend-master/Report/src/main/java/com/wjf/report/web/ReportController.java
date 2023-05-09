package com.wjf.report.web;

import com.wjf.report.entity.LogReport;

import com.wjf.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    ReportService reportService;


    @GetMapping("/getReports/all")
    public List<LogReport> getAllReports() {
        return reportService.findAll();
    }

    @GetMapping("/getReports/latest")
    public List<LogReport> getLatestReports() {
        return null;
    }

    @GetMapping("/getReports/{serviceName}")
    public List<LogReport> getReportsByServiceName(@PathVariable String serviceName) {
        return reportService.findByName(serviceName);
    }

    @PostMapping("/saveReport")
    public void saveLogInfo(@Valid @RequestBody LogReport logReport) {
        reportService.save(logReport);
    }


}

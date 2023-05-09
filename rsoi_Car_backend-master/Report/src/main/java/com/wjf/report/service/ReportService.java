package com.wjf.report.service;

import com.wjf.report.entity.LogReport;

import java.util.List;

/**
 * @author JunFei
 * @data
 * @purpose
 */
public interface ReportService {
    List<LogReport> findAll();
    List<LogReport> findByName(String name);
    void save(LogReport logReport);
}

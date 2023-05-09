package com.wjf.report.service;

import com.wjf.report.entity.LogReport;
import com.wjf.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

/**
 * @author JunFei
 * @data
 * @purpose
 */
@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    ReportRepository reportRepository;

    @Override
    public List<LogReport> findAll() {
        return reportRepository.findAllByOrderByIdDesc();
    }



    @Override
    public List<LogReport> findByName(String name) {
        return reportRepository.findLogReportsByName(name)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Reports not found by service %s", name)));
    }

    @Override
    public void save(LogReport logReport) {
        reportRepository.save(logReport);
    }
}

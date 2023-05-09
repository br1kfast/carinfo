package com.wjf.report.repository;


import com.wjf.report.entity.LogReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author JunFei
 * @data
 * @purpose
 */
public interface ReportRepository extends JpaRepository<LogReport,Integer> {

    Optional<List<LogReport>> findLogReportsByName(String name);

    @Query(value = "select max(id) from log_report", nativeQuery = true)
    Integer maxId();

    List<LogReport> findAllByOrderByIdDesc();

}

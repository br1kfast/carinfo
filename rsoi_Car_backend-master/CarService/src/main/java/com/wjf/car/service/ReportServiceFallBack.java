package com.wjf.car.service;


import com.wjf.car.entity.LogReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author JunFei
 * @data
 * @purpose
 */
@Component
public class ReportServiceFallBack implements ReportService{

    private static final Logger logger = LoggerFactory.getLogger(ReportServiceFallBack.class);

    @Override
    public void saveLogInfo(LogReport logReport) {
        logger.info("FallBack: CarServiceFallBack");
    }

}

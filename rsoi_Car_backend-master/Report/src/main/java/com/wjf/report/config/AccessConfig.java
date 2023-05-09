package com.wjf.report.config;


import com.wjf.report.filter.AccessFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author JunFei
 * @data
 * @purpose 过滤器配置
 */
@Configuration
public class AccessConfig {
    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AccessFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

}

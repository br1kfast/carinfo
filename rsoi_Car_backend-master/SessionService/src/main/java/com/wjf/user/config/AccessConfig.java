package com.wjf.user.config;

import com.wjf.user.filter.AccessFilter;
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

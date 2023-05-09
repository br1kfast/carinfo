package com.wjf.report.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author JunFei
 * @data
 * @purpose 创建一个防止跨越网关进行访问的过滤器
 */
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        //从请求头中获取参数，如果匹配则放行
        String gateway = request.getHeader("gateway");
        if (gateway.equals("zuul"))
            filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

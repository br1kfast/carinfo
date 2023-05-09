package com.wjf.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author JunFei
 * @data
 * @purpose 创建过滤器 在网关中进行访问权限的验证 放行或拦截
 */

@Component
@Slf4j
@Configuration
public class UrlFilter extends ZuulFilter {

    private Map<String,Set<String>> urls= new LinkedHashMap<>();

    public UrlFilter()
    {
        super();
        //分配用户权限
        Set<String> users=new HashSet<>();
        users.add("/car/cars/findall");
        users.add("/car/cars/carBrand/*");
        users.add("/car/cars/*");
        urls.put("user",users);
        //分配管理员权限
        Set<String> admins=new HashSet<>();
        admins.add("/car/cars/update");
        admins.add("/car/car/create");
        admins.add("/car/car/delete/*");
        admins.add("/reports/reports/getReports/all");
        admins.add("/reports/reports/getReports/*");
        admins.add("/reports/reports/saveReport");
        urls.put("admin",admins);
    }

    //在请求被路由之前调用。
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    //是否需要过滤
    @Override
    public boolean shouldFilter() {
        return  true;
    }

    @Override
    public Object run()  {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String uri=request.getRequestURI();
        //uri匹配
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        String token= JwtUtils.getRequestToken(request);

        //未登录 只能访问登录注册接口
        if(token == null  ){
            if(uri.contains("session")){
                ctx.addZuulRequestHeader("gateway","zuul");
                return null;
            }else{
                ctx.setSendZuulResponse(false); //拦截过滤该请求
                ctx.setResponseBody("please login");
                ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                return "please login";
            }
        }
        String role=JwtUtils.getRole(token.substring(7));

        if("admin".equals(role)){
            //转发token到服务
            ctx.addZuulRequestHeader("token",token.substring(7));
            ctx.addZuulRequestHeader("gateway","zuul"); //用于检查防止越过网关直接请求服务的请求
            return  null;
        }
        //不是管理员 试图访问管理员的接口
        Set<String> set=urls.get("admin");
        for(String string: set)
        {
            if(antPathMatcher.match(string,uri)){
                ctx.setSendZuulResponse(false);
                ctx.setResponseBody("you do not have enough right");
                ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
                return "you do not have enough right";
            }
        }
        //转发token到服务
        ctx.addZuulRequestHeader("token",token.substring(7));
        ctx.addZuulRequestHeader("gateway","zuul");
        return  null;
    }
}

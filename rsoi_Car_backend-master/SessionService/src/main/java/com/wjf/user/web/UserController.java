package com.wjf.user.web;

import com.wjf.user.entity.LogReport;
import com.wjf.user.utils.AjaxResult;
import com.wjf.user.entity.User;
import com.wjf.user.utils.JwtUtils;
import com.wjf.user.service.ReportService;
import com.wjf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author JunFei
 * @data
 * @purpose
 */

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Qualifier("com.wjf.user.service.ReportService")
    @Autowired
    ReportService reportService;



    @PostMapping("")
    public String CreateUser(@Valid @RequestBody User user,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);

        return userService.CreateUser(user);
    }

    //登录
    @PostMapping("/login")
    public AjaxResult Login(@Valid @RequestBody User user,HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        String serverName = request.getServerName();
        LogReport logReport = new LogReport();
        logReport.setMethodType(method);
        logReport.setName(serverName);
        logReport.setPath(requestURI);
        reportService.saveLogInfo(logReport);

        if (userService.login(user)) {
            String name = user.getName();
            User user2 = userService.getUser(name);
            String role = user2.getRole();
            String token= JwtUtils.sign(Integer.toString(user2.getUserUid()),user2.getRole()); //生成JWT
            if (token != null) {
                return AjaxResult.success(role, token);

            }
        }

        return AjaxResult.fail();
    }


}

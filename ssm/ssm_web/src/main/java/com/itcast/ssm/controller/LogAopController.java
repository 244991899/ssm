package com.itcast.ssm.controller;

import com.itcast.ssm.domain.SysLog;
import com.itcast.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAopController {
    @Autowired
    private ISysLogService iSysLogService;
    @Autowired
    private HttpServletRequest request;  //首先要配置监听器这个类对象//配置监听器RequestContextListener
    private Date visitTime; //访问时间
    private Method method;   //访问方法
    private Object clazz;  //访问的类
    @Before("execution(* com.itcast.ssm.controller..*.*(..))")
    private void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();
        Object[] jpArgs = jp.getArgs(); //获取到所有的参数集合
        String methodName = jp.getSignature().getName(); //获取方法名字
        clazz = jp.getTarget(); //获取被代理对象
        if(jpArgs==null||jpArgs.length==0){
            method = clazz.getClass().getMethod(methodName);
        }else {
            Class[] jpArgsClass = new Class[jpArgs.length];
            for (int i = 0; i < jpArgs.length; i++) {
                jpArgsClass[i] = jpArgs[i].getClass();
            }
            method = clazz.getClass().getMethod(methodName, jpArgsClass);
        }

    }
    @After("execution(* com.itcast.ssm.controller..*.*(..))")
    private void doAfter(JoinPoint jp){
        if(clazz!=null&&method!=null&&clazz.getClass()!= LogAopController.class){
            /*获取路径*/
            RequestMapping annotation = clazz.getClass().getAnnotation(RequestMapping.class);
            if(annotation!=null){
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation!=null&&methodAnnotation.value().length!=0){
                    String url = annotation.value()[0] + methodAnnotation.value()[0];
                    Long executionTime = new Date().getTime() - visitTime.getTime();
                    String ip = request.getRemoteAddr();
                    //String header = request.getHeader("x-forwarded-for");  获取真实id
                    User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    String username = user.getUsername();
                    SysLog sysLog = new SysLog();
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setIp(ip);
                    sysLog.setMethod(method.getName());
                    sysLog.setUrl(url);
                    sysLog.setUsername(username);
                    sysLog.setVisitTime(visitTime);
                    iSysLogService.save(sysLog);
                }
            }
        }

    }
}

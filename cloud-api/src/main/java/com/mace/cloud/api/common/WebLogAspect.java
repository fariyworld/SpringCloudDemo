package com.mace.cloud.api.common;

import com.mace.cloud.api.annotation.SystemControllerLog;
import com.mace.cloud.api.entity.User;
import com.mace.cloud.api.util.StringHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * description: web请求 aop统一日志
 * <br />
 * Created by mace on 20:47 2018/5/28.
 */
@Order(0)//index的值越小，所对应的优先级就越高
@Aspect
@Component
@Slf4j
public class WebLogAspect {

    //解决 AOP 切面中的同步问题
    ThreadLocal<Long> START_TIME = new ThreadLocal<Long>();
    ThreadLocal<Long> END_TIME = new ThreadLocal<Long>();
    ThreadLocal<String> requestId = new ThreadLocal<String>();

//    private long START_TIME;
//    private long END_TIME;
//    private String requestId;

    // 定义在controller包和所有子包里的任意类的任意方法的执行
    @Pointcut("execution(* com.mace.cloud.controller..*.*(..))")
    public void ex() {
    }

    //execution(public * com.mace.cloud.*.controller..*.*(..))"

    //Controller层切点 注解
    @Pointcut("@annotation(com.mace.cloud.api.annotation.SystemControllerLog)")
    public void controllerAspect() {
    }

    //前置通知
    @Before(value = "ex()")
    public void doBefore(JoinPoint joinPoint){

        requestId.set(StringHelper.getUUIDString());
        START_TIME.set(System.currentTimeMillis());
//        requestId = StringHelper.getUUIDString();
//        START_TIME = System.currentTimeMillis();

        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();
        //读取session中的用户
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constant.CURRENT_USER);
        String username = "";
        if( user != null)       username = user.getUsername();

        log.info("=========================请求 start======================================");
        log.info("requestId : " + requestId);
        log.info("请求人 : " + username);
        log.info("IP : " + getClientIp(request));
        log.info("URL : "+ getRequestUrl(request));
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName()
                + "." + joinPoint.getSignature().getName());
        log.info("方法描述 : " + getControllerMethodDescription(joinPoint));

        /*==========数据库日志=========*/
//        Log log = SpringContextHolder.getBean("logxx");
//        log.setDescription(getServiceMthodDescription(joinPoint));
//        log.setExceptionCode(e.getClass().getName());
//        log.setType("1");
//        log.setExceptionDetail(e.getMessage());
//        log.setMethod((joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//        log.setParams(params);
//        log.setCreateBy(user);
//        log.setCreateDate(DateUtil.getCurrentDate());
//        log.setRequestIp(ip);
        //保存数据库
//        logService.add(log);

        Map<String, String[]> parameterMap = request.getParameterMap();
        if ( parameterMap.size() > 0 ){
            log.info("request参数===========================================start");
            Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
            for(Map.Entry<String, String[]> entry:entrySet){
                String key = entry.getKey();
                if(key.equals("password")){
                    log.info("name: { "+key+" }, value: {  }");
                }else{
                    log.info("name: { "+key+" }, value: { "+request.getParameter(key)+" }");
                }
            }
            log.info("request参数===========================================end");
        }

        if(!Arrays.toString(joinPoint.getArgs()).equals("[{}]") && !Arrays.toString(joinPoint.getArgs()).equals("[]")){
            log.info("URL_PATH参数: {}",Arrays.toString(joinPoint.getArgs()));
        }
    }

    //后置通知
    @After("ex()")
    public void doAfter(JoinPoint joinPoint){
    }

    //异常通知
    @AfterThrowing(throwing = "ex", pointcut = "ex()")
    public void doAfterThrowing(JoinPoint joinPoint, Exception ex){

        END_TIME.set(System.currentTimeMillis());
//        END_TIME = System.currentTimeMillis();
        ServletRequestAttributes requestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        HttpServletRequest request = requestAttributes.getRequest();

        request.setAttribute("requestId", requestId.get());
        request.setAttribute("START_TIME", START_TIME.get());
        request.setAttribute("END_TIME", END_TIME.get());
//        request.setAttribute("requestId", requestId);
//        request.setAttribute("START_TIME", START_TIME);
//        request.setAttribute("END_TIME", END_TIME);

        log.error("异常信息: {}",ex.getMessage());
        log.error("异常堆栈: {}",ex.getStackTrace());
        log.info("=========================请求 异常 end======================================");
    }

    //返回通知
    @AfterReturning(returning = "result",pointcut = "ex()")
    public void doAfterReturning(Object result){
        // 处理完请求，返回内容
        END_TIME.set(System.currentTimeMillis());
//        END_TIME = System.currentTimeMillis();

        if(result instanceof RestPackResponse){

            RestPackResponse response = (RestPackResponse) result;

            response.setRequestId(requestId.get());
            response.setServerTime(new Date(START_TIME.get()));
            response.setSpendTime((END_TIME.get() - START_TIME.get()));
//            response.setRequestId(requestId);
//            response.setServerTime(new Date(START_TIME));
//            response.setSpendTime((END_TIME - START_TIME));
        }

        log.info("RESPONSE_TYPE : " + result.getClass());
        log.info("RESPONSE : " + result);
        log.info("=========================请求 end======================================");
        System.out.println("\t");
    }

    //环绕通知
    //@Around(value = "ex()")
    public Object doAround(ProceedingJoinPoint pjd){
        Object result =null;

        try {
            // 环绕前

            // 前置通知

            // 执行被代理的方法
            result = pjd.proceed();

            // 环绕后

        } catch (Throwable throwable) {
            // 异常通知 (如果有返回通知，则在返回通知之后执行)
            System.out.println(throwable.getMessage());
        }

        //后置通知

        // 返回通知

        return result;
    }

    public static String getClientIp(HttpServletRequest request){

        String remoteAddr = StringUtils.EMPTY;

        if(request!=null){

            remoteAddr = request.getHeader("X-FORWARDED-FOR");

            if(remoteAddr==null || "".equals(remoteAddr)){

                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }


    public static String getRequestUrl(HttpServletRequest request){

        String url = StringUtils.EMPTY;

        try {
            url =  URLDecoder.decode(request.getRequestURL().toString(), Constant.DEFAULT_ENCODING_CHARTSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    private  String getControllerMethodDescription(JoinPoint joinPoint){
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = null;
        try {
            targetClass = Class.forName(targetName);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage());
        }
        Method[] methods = targetClass.getMethods();
        String description = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    description = method.getAnnotation(SystemControllerLog. class).description();
                    break;
                }
            }
        }
        return description;
    }
}

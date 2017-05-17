package com.nextcont.drive.aspect;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.TokenInfo;
import com.nextcont.file.request.file.FileListRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/11
 * Time: 14:51
 * To change this template use File | Settings | File Templates.
 */
@Aspect
@Component
@Slf4j
@Order(5)
public class ParamAspect extends AbstractAspect{


    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.nextcont.drive.controller..*.*(..))) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void paramCheckPointCut() {
    }


    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("paramCheckPointCut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    public Object Interceptor(ProceedingJoinPoint pjp) {

        long beginTime = System.currentTimeMillis();

        Object result = null;

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod(); //获取被拦截的方法
        String methodName = method.getName(); //获取被拦截的方法名


        log.info("aop 参数检查，方法：{}", methodName);

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            if (arg instanceof FileListRequest) {
                FileListRequest request = (FileListRequest) arg;
                if(request.getPageSize()<0 || request.getPageToken()<0)
                    result = "pageSize or PageToken check abnormal.";
                if(request.getQ()==null||("").equals(request.getQ()))
                    request.setQ("trashed=\"false\" and sharedWithMe= \"false\"");
            }
        }

        result = procceed(pjp);

        if (result instanceof String) {
            long costMs = System.currentTimeMillis() - beginTime;
            log.info("{}请求结束，耗时：{}ms", "HttpServletRequest", costMs);
        }
        return result;
    }

}

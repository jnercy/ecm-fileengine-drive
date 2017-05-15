package com.nextcont.drive.aspect;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.TokenInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/8
 * Time: 13:57
 * To change this template use File | Settings | File Templates.
 */

@Aspect
@Component
@Slf4j
@Order(1)
public class AuthAspect {

    private static ThreadLocal<TokenInfo> localTokenInfo = new ThreadLocal<>();


    @Value("${oauth.url}")
    private String host;

    @Value("${oauth.switch}")
    private String oauthSwitch;

    /**
     * 定义拦截规则：拦截com.xjj.web.controller包下面的所有类中，有@RequestMapping注解的方法。
     */
    @Pointcut("execution(* com.nextcont.drive.controller..*.*(..))) and @annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public void controllerMethodPointcut() {

    }


    /**
     * 拦截器具体实现
     *
     * @param pjp
     * @return JsonResult（被拦截方法的执行结果，或需要登录的错误提示。）
     */
    @Around("controllerMethodPointcut()") //指定拦截器规则；也可以直接把“execution(* com.xjj.........)”写进这里
    public Object Interceptor(ProceedingJoinPoint pjp) {

        Object result = null;

        if (oauthSwitch.equals("close")) {
            try {
                TokenInfo tokenInfo = new TokenInfo();
                tokenInfo.setGmail("mengping.jin@nextcont.com");
                tokenInfo.setPhotoLink("www.baidu.com");
                tokenInfo.setHd("nextcont.com");
                tokenInfo.setRootid("0APymvC2SzZuDUk9PVA");
                tokenInfo.setGstate(true);
                localTokenInfo.set(tokenInfo);
                result = pjp.proceed();
            } catch (Throwable e) {
                e.printStackTrace();
             }
        } else {
            long beginTime = System.currentTimeMillis();
            MethodSignature signature = (MethodSignature) pjp.getSignature();
            Method method = signature.getMethod(); //获取被拦截的方法
            String methodName = method.getName(); //获取被拦截的方法名

            log.info("请求开始，方法：{}", methodName);

            Object[] args = pjp.getArgs();
            for (Object arg : args) {
                if (arg instanceof HttpServletRequest) {
                    HttpServletRequest request = (HttpServletRequest) arg;
                    Map<String, String> headerMap = new HashMap<>();
                    headerMap.put("authorization", "OAuth " + request.getHeader("token"));
                    Tuple<Integer, String> authInfo = HttpClient.httpGetRequest("https://" + host + "/o/state", headerMap);

                    if (authInfo.v1() == 200) {
                        TokenInfo tokenInfo = JsonFormat.convert2Object(authInfo.v2(), new TokenInfo()).get();
                        localTokenInfo.set(tokenInfo);
                    } else {
                        result = authInfo.v2();
                    }
                }
            }

            try {
                // 一切正常的情况下，继续执行被拦截的方法
                if (result == null)
                    result = pjp.proceed();
            } catch (Throwable e) {
                log.info("exception: ", e);
                result = e.getMessage();
            }

            if (result instanceof String) {
                long costMs = System.currentTimeMillis() - beginTime;
                log.info("{}请求结束，耗时：{}ms", methodName, costMs);
            }
        }
            return result;
        }


        public  static TokenInfo getAuthTokenInfo(){
            return localTokenInfo.get();
        }
}
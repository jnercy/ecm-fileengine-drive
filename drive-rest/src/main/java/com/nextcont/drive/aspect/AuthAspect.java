package com.nextcont.drive.aspect;

import com.nextcont.drive.utils.HttpClient;
import com.nextcont.drive.utils.JsonFormat;
import com.nextcont.drive.utils.Tuple;
import com.nextcont.file.TokenInfo;
import com.nextcont.file.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
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
public class AuthAspect extends AbstractAspect{

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
        long beginTime = System.currentTimeMillis();
        if (oauthSwitch.equals("close")) {
            try {
                RequestAttributes ra = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes sra = (ServletRequestAttributes) ra;
                HttpServletRequest request = sra.getRequest();
                log.info("token test=>{}",request.getHeader("token"));

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
            log.info("获取请求HttpServletRequest.");
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("authorization", "OAuth " + request.getHeader("token"));
            Tuple<Integer, String> authInfo = HttpClient.httpGetRequest("https://" + host + "/o/state", headerMap);

            if (authInfo.v1() == 200) {
                TokenInfo tokenInfo = JsonFormat.convert2Object(authInfo.v2(), new TokenInfo()).get();
                localTokenInfo.set(tokenInfo);
                result = procceed(pjp);
            } else
                result = authInfo.v2();
        }

        if (result instanceof String) {
            long costMs = System.currentTimeMillis() - beginTime;
            log.info("{}请求结束，耗时：{}ms", "HttpServletRequest", costMs);
            result = new ResponseEntity<>(ErrorResponse.createErrorResponse(HttpStatus.BAD_REQUEST.value(),result.toString()), HttpStatus.BAD_REQUEST);
        }
        return result;
    }


    public static TokenInfo getAuthTokenInfo() {
        return localTokenInfo.get();
    }
}
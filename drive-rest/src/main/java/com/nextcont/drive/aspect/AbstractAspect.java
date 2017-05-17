package com.nextcont.drive.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 * Created with IntelliJ IDEA.
 * User: Wangxudong
 * Date: 2017/5/16
 * Time: 11:03
 * To change this template use File | Settings | File Templates.
 */
public class AbstractAspect {

     Object procceed(ProceedingJoinPoint pjp){
         Object result;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
            result = e.getMessage();
        }
        return result;
    }
}

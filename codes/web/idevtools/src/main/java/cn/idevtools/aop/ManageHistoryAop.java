package cn.idevtools.aop;

import cn.idevtools.util.HttpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 写入管理记录
 * 未完待续
 * @author 王沁宽
 */
@Aspect
@Component
public class ManageHistoryAop {

    @Pointcut("@annotation(cn.idevtools.common.annotation.AddManageHistory)")
    public void manageHistoryPointCut(){}

    @Around("manageHistoryPointCut()")
    public Object addManageHistory(ProceedingJoinPoint joinPoint){
        Object result=null;

        try{
            result= joinPoint.proceed();
        }catch (Throwable e){
            e.printStackTrace();
        }
        return result;
    }
}

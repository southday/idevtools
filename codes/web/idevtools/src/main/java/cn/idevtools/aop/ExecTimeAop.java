package cn.idevtools.aop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 打印方法的执行时间,只对配置了{@link cn.idevtools.common.annotation.PrintExecTime} 注解的方法生效
 *
 * @author 王沁宽
 *
 */
@Aspect
@Component
public class ExecTimeAop {

    private static final Logger logger = LogManager.getLogger(ExecTimeAop.class);

    @Pointcut("@annotation(cn.idevtools.common.annotation.PrintExecTime)")
    public void execTimePointCut(){}

    @Around("execTimePointCut()")
    public Object printExecTime(ProceedingJoinPoint joinPoint){
        Object result=null;
        try{
            String methodName=joinPoint.getSignature().getName();
            long startTime=System.currentTimeMillis();
            System.out.println("方法: "+methodName+"---开始执行");
            result=joinPoint.proceed();
            long endTime=System.currentTimeMillis();
            System.out.println("方法: "+methodName+"---执行完毕，耗时："+(endTime-startTime)+"ms");
        }catch (Throwable e){
            e.printStackTrace();
        }
        return result;
    }

}

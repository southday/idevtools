package cn.idevtools.aop;

import cn.idevtools.common.CommonConst;
import cn.idevtools.common.annotation.AddManageHistory;
import cn.idevtools.common.builder.ManageHistoryBuilder;
import cn.idevtools.po.ManageHistoryT;
import cn.idevtools.service.ManageHistoryService;
import cn.idevtools.util.CookieUtil;
import cn.idevtools.util.JWTUtil;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 写入操作历史.
 * 只写管理员的操作历史.
 * 只记录带{@link AddManageHistory}注解的方法
 * @author 王沁宽
 */
@Aspect
@Component
public class ManageHistoryAop {

    @Autowired
    ManageHistoryService manageHistoryService;

    @Pointcut("@annotation(cn.idevtools.common.annotation.AddManageHistory)")
    public void manageHistoryPointCut(){}

    @Around("manageHistoryPointCut()")
    public Object addManageHistory(ProceedingJoinPoint joinPoint){
        Object result = null;
        ManageHistoryBuilder manageHistoryBuilder=new ManageHistoryBuilder();
        try{
            result = joinPoint.proceed();
            String jws = CookieUtil.getCookieValue(CommonConst.TOKEN);
            if(jws != null && jws.trim().length() > 0){
                Claims claims = JWTUtil.getClaims(jws);
                //判断是否为管理员，只为管理员写入操作历史
                if(claims != null && CommonConst.USER_TYPE_ADMIN.equals(claims.get(CommonConst.USER_TYPE, String.class))){
                    Method method = ((MethodSignature)joinPoint.getSignature()).getMethod();
                    //获取注解信息
                    AddManageHistory addManageHistory = method.getAnnotation(AddManageHistory.class);
                    //构建操作历史对象
                    ManageHistoryT manageHistory = manageHistoryBuilder.setAdminId(claims.get(CommonConst.ID, Integer.class))
                            .setAdminName(claims.get(CommonConst.USER_NAME, String.class))
                            .setActionDesc(addManageHistory.ACTION_DESC())
                            .setActionType(addManageHistory.ACTION_TYPE().toString())
                            .setActionTarget(method.getName())
                            .buildManageHistory();
                    manageHistoryService.addManageHistory(manageHistory);
                }
            }
        }catch (Throwable e){
            e.printStackTrace();
        }
        return result;
    }
}

package cn.idevtools.interceptor;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.util.ValidUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 验证码拦截器
 * @author southday
 * @date 2019/5/17
 */
public class JCaptchaInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object Handler) throws Exception {
        if (!ValidUtil.isPassCaptcha(req)) {
            resp.reset();
            resp.setCharacterEncoding("utf-8");
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter pw = resp.getWriter();
            pw.write(JSONObject.toJSONString(new Message<>(CodeMsgE.CAPTCHA_ERROR)));
            pw.flush();
            pw.close();
            return false;
        }
        return true;
    }
}

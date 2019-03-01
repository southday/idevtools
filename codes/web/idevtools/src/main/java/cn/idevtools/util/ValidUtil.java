package cn.idevtools.util;

import cn.idevtools.common.CommonConst;
import cn.idevtools.common.ValidMsg;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 表单验证的工具类
 *
 * @author 王沁宽
 */
public class ValidUtil {
    private static final Pattern emailPattern;
    static {
        String regx = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        emailPattern = Pattern.compile(regx);
    }

    /**
     * 将表单检验结果BindingResult转为ValidMsg形式
     * @return
     */
    public static List<ValidMsg> toValidMsgs(BindingResult bindingResult){
        List<FieldError> errors = bindingResult.getFieldErrors();
        List<ValidMsg> validMsgs = new ArrayList<>();
        for(FieldError error : errors){
            validMsgs.add(new ValidMsg(error.getField(),error.getDefaultMessage()));
        }
        return validMsgs;
    }

    /**
     * 传入的参数是否所有都为非空字符串<br> southday 2019.02.27
     * 非空字符串：!= null && 非空白字符串
     * @param params
     * @return 所有的字符串均为非空字符串时，返回true，否则返回false
     */
    public static boolean notNullString(String... params) {
        if (params == null || params.length == 0)
            return false;
        for (String p : params)
            if (p == null || "".equals(p.trim()))
                return false;
        return true;
    }

    /**
     * 验证邮箱是否合法 southday 2019.02.28
     * @param email
     * @return
     */
    public static boolean isEmailValid(String email) {
        return notNullString(email) && emailPattern.matcher(email).matches();
    }

    /**
     * 校验验证码 southday 2019.02.28
     * @param req
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassCaptcha(HttpServletRequest req) {
        String captchaResp = req.getParameter(CommonConst.JCAPTCHA);
        return SimpleImageCaptchaServlet.validateResponse(req, captchaResp);
    }

    /**
     * 校验验证码 southday 2019.02.28
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassCaptcha() {
        return isPassCaptcha(HttpUtil.getHttpServletRequest());
    }
}

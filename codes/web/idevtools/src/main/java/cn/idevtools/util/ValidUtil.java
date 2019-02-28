package cn.idevtools.util;

import cn.idevtools.common.ValidMsg;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * 表单验证的工具类
 *
 * @author 王沁宽
 */
public class ValidUtil {

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
}

package cn.idevtools.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 通用Service
 * @author southday
 * @date 2019/2/27
 */
@Service
public interface CommonService {
    /**
     * 校验验证码
     * @param req
     * @return 通过返回true，否则false
     */
    boolean checkCaptcha(HttpServletRequest req);
}

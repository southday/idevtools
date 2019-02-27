package cn.idevtools.service.impl;

import cn.idevtools.common.CommonConst;
import cn.idevtools.service.CommonService;
import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author southday
 * @date 2019/2/27
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Override
    public boolean checkCaptcha(HttpServletRequest req) {
        String captchaResp = req.getParameter(CommonConst.JCAPTCHA);
        return SimpleImageCaptchaServlet.validateResponse(req, captchaResp);
    }
}

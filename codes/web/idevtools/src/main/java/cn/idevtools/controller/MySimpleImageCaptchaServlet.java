package cn.idevtools.controller;

import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.image.DefaultManageableImageCaptchaService;
import com.octo.captcha.service.image.ImageCaptchaService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 自定义JCaptcha验证码Servlet，由于源码中捕获到异常后直接e.printStackTrace()，其在本地开发调试时，控制台有输出，而部署到服务器时却无输出。
 * 导致程序出错，却不知道哪里错了，所以使用自定义的Servlet，出现异常时用Logger把错误信息打印出来，方便调试；
 * @author southday
 * @date 2019/6/13
 */
public class MySimpleImageCaptchaServlet extends HttpServlet implements Servlet {
    private static final Logger logger = LogManager.getLogger(MySimpleImageCaptchaServlet.class);
    public static ImageCaptchaService service = new DefaultManageableImageCaptchaService();

    public MySimpleImageCaptchaServlet() {}

    protected void doGet(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws ServletException, IOException {
        httpServletResponse.setDateHeader("Expires", 0L);
        httpServletResponse.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        httpServletResponse.addHeader("Cache-Control", "post-check=0, pre-check=0");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setContentType("image/jpeg");
        BufferedImage bi = service.getImageChallengeForID(httpServletRequest.getSession(true).getId());
        ServletOutputStream out = httpServletResponse.getOutputStream();
        ImageIO.write(bi, "jpg", out);

        try {
            out.flush();
        } finally {
            out.close();
        }

    }

    public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
        if (request.getSession(false) == null) {
            return false;
        } else {
            boolean validated = false;
            try {
                validated = service.validateResponseForID(request.getSession().getId(), userCaptchaResponse);
            } catch (CaptchaServiceException var4) {
                logger.warn("验证码验证异常：" + ExceptionUtils.getStackTrace(var4));
            }
            return validated;
        }
    }
}

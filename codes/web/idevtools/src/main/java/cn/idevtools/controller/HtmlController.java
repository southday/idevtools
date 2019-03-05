package cn.idevtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 静态页面请求处理Controller
 * @author southday
 * @date 2019/3/4
 */
@Controller
public class HtmlController {

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchHtml() {
        return "isearch";
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public String viewHtml() {
        return "iview";
    }
}

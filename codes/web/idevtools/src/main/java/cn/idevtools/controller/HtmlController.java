package cn.idevtools.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 静态页面请求处理Controller
 * @author southday
 * @date 2019/3/4
 */
@Controller
public class HtmlController {

    /**
     * 首页获取静态页面isearch.html
     * southday 2019.03.04
     * @return
     */
    @GetMapping("/search")
    public String searchHtml() {
        return "isearch";
    }

    /**
     * 首页获取静态页面iview.html
     * southday 2019.03.06
     * @return
     */
    @GetMapping("/view")
    public String viewHtml() {
        return "iview";
    }
}

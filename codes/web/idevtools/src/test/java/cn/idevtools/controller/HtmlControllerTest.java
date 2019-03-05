package cn.idevtools.controller;

import cn.idevtools.CommonTest;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * 返回静态页面测试
 * @author southday
 * @date 2019/3/4
 */
public class HtmlControllerTest extends CommonTest {

    /**
     * 请求静态页面search.html测试<br/>
     * 打印返回的页面
     * @throws Exception
     */
    @Test
    public void searchHtmlTest() throws Exception {
        MvcResult mvc = mockMvc.perform(
                MockMvcRequestBuilders.get("/search")
        ).andReturn();
        System.out.println(mvc.getResponse().getContentAsString());
    }
}

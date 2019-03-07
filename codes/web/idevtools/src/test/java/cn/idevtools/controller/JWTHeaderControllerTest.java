package cn.idevtools.controller;

import cn.idevtools.CommonTest;
import cn.idevtools.common.CommonConst;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author southday
 * @date 2019/3/5
 */
public class JWTHeaderControllerTest extends CommonTest {

    @Test
    public void foo() throws Exception {
        MvcResult mvc = mockMvc.perform(
                MockMvcRequestBuilders.get("/test/u/{userId}", 67)
        ).andReturn();
        MockHttpServletResponse resp = mvc.getResponse();
        System.out.println("token : " + resp.getHeader(CommonConst.TOKEN));
        System.out.println(resp.getContentAsString());
    }
}


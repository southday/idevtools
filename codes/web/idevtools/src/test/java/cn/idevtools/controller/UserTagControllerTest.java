package cn.idevtools.controller;

import cn.idevtools.CommonTest;
import cn.idevtools.po.UserTagT;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

/**
 * 使用MockMvc 对UserTagController的方法进行测试
 * @see cn.idevtools.controller.UserTagController
 * @author 王沁宽
 */
public class UserTagControllerTest extends CommonTest {
    /**
     * 测试方法{@link UserTagController#addUserTag(UserTagT, BindingResult)} )}
     * 打印返回的json
     */
    @Test
    public void addUserTagTest() throws Exception{
        MvcResult mvcResult=mockMvc.perform(
                MockMvcRequestBuilders.post("/userTag/add")
                        .param("tagMeaning","testmean")
                        .param("description","null")
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }
}

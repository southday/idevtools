package cn.idevtools.controller;

import cn.idevtools.CommonTest;
import cn.idevtools.po.UserT;
import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;




/**
 * 使用MockMvc 对UserController的方法进行测试
 * @see cn.idevtools.controller.UserController
 * @author 王沁宽
 */

public class UserControllerTest extends CommonTest {
    /**
     * 测试方法{@link UserController#getUserDetailWithTagById}
     * 打印返回的json
     */
    @Test
    public void getUserDetailWithTagByIdTest() throws Exception{
        //返回userId为1的用户详情
        String resp=mockMvc.perform(
                MockMvcRequestBuilders.get("/user/userDetailWithTag.json/{userId}",2)
        ).andReturn().getResponse().getContentAsString();
        System.out.println(resp);
    }

    /**
     * 测试方法{@link UserController#getSearchedUserInfoByPage(UserT, Integer)}
     * 打印返回的json
     */
    @Test
    public void getSearchedUserInfoByPageTest() throws Exception{
        MvcResult mvcResult=mockMvc.perform(
                MockMvcRequestBuilders.post("/user/searchUserInfo.json/page/{pageId}",1)
                .param("userName","1")
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());
    }

    /**
     * 测试方法{@link UserController#addTagForUser(Integer, Integer)}
     */
    @Test
    public void addTagForUserTest() throws Exception{
        MvcResult mvcResult=mockMvc.perform(
                MockMvcRequestBuilders.get("/user/addTagForUser/{userId}/{tagId}",1,1)
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

    /**
     * 测试方法{@link UserController#removeTagForUser(Integer, Integer)}
     */
    @Test
    public void removeTagForUserTest() throws Exception{
        MvcResult mvcResult=mockMvc.perform(
                MockMvcRequestBuilders.get("/user/removeTagForUser/{userId}/{tagId}",1,1)
        ).andReturn();
        System.out.println(mvcResult.getResponse().getContentAsString());

    }

}

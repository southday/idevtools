package cn.idevtools;

import cn.idevtools.po.UserT;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import cn.idevtools.controller.UserController;


/**
 * 使用MockMvc 对UserController的方法进行测试
 * MockMvc 的用法示例 https://www.cnblogs.com/lyy-2016/p/6122144.html
 * 官方文档 https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#spring-mvc-test-server
 * 官方api https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html
 *
 * @see UserController
 * @author 王沁宽
 */

/**
 * webappconfiguration用于声明一个ApplicationContext集成测试加载WebApplicationContext。作用是模拟ServletContext
 * ContextConfiguration：因为controller，component等都是使用注解，需要注解指定spring的配置文件，扫描相应的配置，将类初始化等。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/config/spring-mvc.xml","classpath:/config/spring-mybatis.xml"})
public class UserControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

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

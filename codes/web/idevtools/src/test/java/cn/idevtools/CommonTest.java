package cn.idevtools;

/**
 * 使用MockMvc 测试的公共部分,所有使用MockMvc测试的类均继承此类,所有数据库操作均回滚,不写入数据库
 * MockMvc 的用法示例 https://www.cnblogs.com/lyy-2016/p/6122144.html
 * 官方文档 https://docs.spring.io/spring/docs/current/spring-framework-reference/testing.html#spring-mvc-test-server
 * 官方api https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html *
 *
 * @author 王沁宽
 */

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

/**
 * webappconfiguration用于声明一个ApplicationContext集成测试加载WebApplicationContext。作用是模拟ServletContext
 * ContextConfiguration：因为controller，component等都是使用注解，需要注解指定spring的配置文件，扫描相应的配置，将类初始化等。
 */
@RunWith(JUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:/config/spring-mvc.xml","classpath:/config/spring-mybatis.xml"})
@Transactional
@Rollback
public class CommonTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

}


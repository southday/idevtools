package cn.idevtools.service;

import cn.idevtools.CommonTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 管理员Service 测试模块
 * @author southday
 * @date 2019/2/26
 */
public class AdminServiceTest extends CommonTest {
    @Autowired
    AdminService adminService;

    @Test
    public void isAdminExistsTest() {
        String adminName = "southday";
        System.out.println(adminService.isAdminExists(adminName));

        adminName = "lk23jskdj";
        System.out.println(adminService.isAdminExists(adminName));
    }
}

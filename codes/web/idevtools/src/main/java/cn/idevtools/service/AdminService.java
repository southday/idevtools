package cn.idevtools.service;

import cn.idevtools.common.Message;
import cn.idevtools.po.AdminT;
import org.springframework.stereotype.Service;

/**
 * 管理员模块Service
 * @author southday
 * @date 2019/2/26
 */
@Service
public interface AdminService {
    /**
     * 根据adminName判断管理员是否存在 southday 2019.02.26
     * @param adminName
     * @return
     */
    boolean isAdminExists(String adminName);

    /**
     * 根据adminName和passowrd来进行管理员登陆 southday 2019.02.26
     * @param argAdmin
     * @return
     */
    AdminT login(AdminT argAdmin);

    /**
     * 管理员退出登录 southday 2019.03.01
     * @return
     */
    Message<?> logout();
}

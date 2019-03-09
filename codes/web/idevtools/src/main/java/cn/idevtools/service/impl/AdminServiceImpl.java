package cn.idevtools.service.impl;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.mapper.AdminTMapper;
import cn.idevtools.po.AdminT;
import cn.idevtools.service.AdminService;
import cn.idevtools.util.JWTer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员模块Service Impl
 * @author southday
 * @date 2019/2/26
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminTMapper adminTMapper;

    @Override
    public boolean isAdminExists(String adminName) {
        return adminTMapper.selectAdminCountByName(adminName) != 0;
    }

    @Override
    public AdminT login(AdminT argAdmin) {
        return adminTMapper.selectAdminByNamePassword(argAdmin);
    }

    @Override
    public Message<?> logout() {
        return JWTer.disableLoginedToken() ?
                new Message<>(CodeMsgE.LOGOUT_SUCCESS) :
                new Message<>(CodeMsgE.LOGOUT_ERROR);
    }
}

package cn.idevtools.service.impl;

import cn.idevtools.mapper.AdminTMapper;
import cn.idevtools.po.AdminT;
import cn.idevtools.service.AdminService;
import cn.idevtools.util.ParamValidator;
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
        return adminTMapper.selectAdminByAdminName(adminName) != null;
    }

    @Override
    public AdminT login(AdminT argAdmin) {
        if (!ParamValidator.notNullString(argAdmin.getAdminName(), argAdmin.getPassword()))
            return null;
        return adminTMapper.selectAdminByNamePassword(argAdmin);
    }
}

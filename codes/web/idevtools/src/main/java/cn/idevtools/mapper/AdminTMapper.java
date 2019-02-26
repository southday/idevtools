package cn.idevtools.mapper;

import cn.idevtools.po.AdminT;

public interface AdminTMapper {
    /**
     * 根据adminName查询管理员信息 southday 2019.02.26
     * @param adminName
     * @return
     */
    AdminT selectAdminByAdminName(String adminName);

    /**
     * 根据adminName和password查询指定管理员 southday 2019.02.26
     * @param argAdmin
     * @return
     */
    AdminT selectAdminByNamePassword(AdminT argAdmin);
}
package cn.idevtools.service.impl;

import cn.idevtools.mapper.ManageHistoryTMapper;
import cn.idevtools.po.ManageHistoryT;
import cn.idevtools.service.ManageHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理记录模块实现
 * @author 王沁宽
 * @date 2019/2/27
 */
@Service
public class ManageHistoryServiceImpl implements ManageHistoryService {
    @Autowired
    private ManageHistoryTMapper manageHistoryTMapper;

    @Override
    public int addManageHistory(ManageHistoryT manageHistory) {
        return manageHistoryTMapper.insertManageHistory(manageHistory);
    }
}

package cn.idevtools.service;

import cn.idevtools.po.ManageHistoryT;

/**
 * 你懂的 管理记录模块 {大家懂才是真的懂 :-)}
 * @see cn.idevtools.service.impl.ManageHistoryServiceImpl
 * @author 王沁宽
 * @date 2019/2/27
 */
public interface ManageHistoryService {
    /**
     * 新建一个管理记录，proTime无须设置，自动为当前时间
     * @return
     */
    int addManageHistory(ManageHistoryT manageHistory);
}

package cn.idevtools.mapper;

import cn.idevtools.po.ManageHistoryT;

public interface ManageHistoryTMapper {
    /**
     * 插入操作日志，日期自动设置为now()
     * @param manageHistory
     * @return 成功1失败0
     */
    int insertManageHistory(ManageHistoryT manageHistory);
}
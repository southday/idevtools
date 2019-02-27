package cn.idevtools.common.builder;

import cn.idevtools.po.ManageHistoryT;

/**
 * 构建历史记录
 * @author 王沁宽
 */
public class ManageHistoryBuilder {

    private ManageHistoryT manageHistory;

    public ManageHistoryBuilder(){
        manageHistory=new ManageHistoryT();
    }

    public ManageHistoryBuilder setAdminId(Integer adminId){
        manageHistory.setAdminId(adminId);
        return this;
    }

    public ManageHistoryBuilder setAdminName(String adminName){
        manageHistory.setAdminName(adminName);
        return this;
    }

    public ManageHistoryBuilder setActionType(String actionType){
        manageHistory.setActionType(actionType);
        return this;
    }

    public ManageHistoryBuilder setActionTarget(String actionTarget){
        manageHistory.setActionType(actionTarget);
        return this;
    }

    public ManageHistoryBuilder setActionDesc(String actionDesc){
        manageHistory.setActionType(actionDesc);
        return this;
    }

    public ManageHistoryT buildManageHistory(){
        return manageHistory;
    }
}

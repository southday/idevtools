package cn.idevtools.service;

import cn.idevtools.CommonTest;
import cn.idevtools.common.builder.ManageHistoryBuilder;
import cn.idevtools.po.ManageHistoryT;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

/**
 * 管理记录Service 测试模块
 * @author 王沁宽
 */
public class ManageHistoryServiceTest extends CommonTest {

    @Autowired
    ManageHistoryService manageHistoryService;

    @Test
    public void addManageHistoryTest(){
        ManageHistoryT manageHistoryT=new ManageHistoryBuilder()
                .setAdminName("ddd")
                .buildManageHistory();

        System.out.println(manageHistoryT.getAdminName());

        manageHistoryService.addManageHistory(manageHistoryT);
    }
}

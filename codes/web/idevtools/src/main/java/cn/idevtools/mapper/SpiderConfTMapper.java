package cn.idevtools.mapper;

import cn.idevtools.po.SpiderConfT;
import java.util.List;


public interface SpiderConfTMapper {

    /**
     * 查看所有爬出配置
     * @return
     */
    List<SpiderConfT> selectAllSpiderConf();
}

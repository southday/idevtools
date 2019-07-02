package cn.idevtools.service.impl;

import cn.idevtools.mapper.SpiderConfTMapper;
import cn.idevtools.po.SpiderConfT;
import cn.idevtools.service.SpiderConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 爬虫配置信息管理
 * @author 王沁宽
 * @date 2019/5/27
 */
@Service
public class SpiderConfServiceImpl implements SpiderConfService {

    @Autowired
    private SpiderConfTMapper spiderConfTMapper;

    @Override
    public List<SpiderConfT> getAllSpiderConf() {
        return spiderConfTMapper.selectAllSpiderConf();
    }
}

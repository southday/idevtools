package cn.idevtools.service;

import cn.idevtools.po.SpiderConfT;

import java.util.List;

/**
 * 爬虫配置信息管理
 * @author 王沁宽
 * @date 2019/5/27
 */
public interface SpiderConfService {
   List<SpiderConfT> getAllSpiderConf();
}

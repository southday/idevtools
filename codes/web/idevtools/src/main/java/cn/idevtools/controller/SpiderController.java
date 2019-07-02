package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.po.SpiderConfT;
import cn.idevtools.service.SpiderConfService;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * 爬虫管理
 * @author 王沁宽
 * @date 2019/5/27
 */
@Controller
@RequestMapping("/spider")
public class SpiderController {
    @Autowired
    private SpiderConfService spiderConfService;

    @GetMapping("/conf")
    @ResponseJSONP
    public Message getAllSpiderConf(){
        List<SpiderConfT> spiderConfs = spiderConfService.getAllSpiderConf();
        return spiderConfs == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取爬虫配置信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,spiderConfs);
    }
}

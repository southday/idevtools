package cn.idevtools.controller;

import cn.idevtools.common.CodeMsgE;
import cn.idevtools.common.Message;
import cn.idevtools.po.ManageHistoryT;
import cn.idevtools.po.ToolT;
import cn.idevtools.redis.Recommend;
import cn.idevtools.service.CommonService;
import cn.idevtools.service.ManageHistoryService;
import cn.idevtools.util.JWTer;
import com.alibaba.fastjson.support.spring.annotation.ResponseJSONP;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {
    @Autowired
    ManageHistoryService manageHistoryService;

    @ResponseJSONP
    @GetMapping("/all")
    public Message getAllLog(){
        List<ManageHistoryT> logs = manageHistoryService.getAllLog();
        return logs == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取日志信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,logs);
    }

    @ResponseJSONP
    @GetMapping("/{adminName}")
    public Message getAllLog(@PathVariable String adminName){
        List<ManageHistoryT> logs = manageHistoryService.searchLogByName(adminName);
        return logs == null ? new Message(CodeMsgE.QUERY_FAILURE,"获取日志信息失败") :
                new Message(CodeMsgE.QUERY_SUCCESS,logs);
    }


}

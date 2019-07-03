package cn.idevtools.service.impl;


import cn.idevtools.mapper.MessageTMapper;
import cn.idevtools.po.MessageT;
import cn.idevtools.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageTMapper messageTMapper;

    @Override
    public int replyMessge(Integer messageId) {
        return messageTMapper.setReply(messageId);
    }

    @Override
    public List<MessageT> getNoReplySuggestion() {
        return messageTMapper.selectSuggestionNoReply();
    }

    @Override
    public List<MessageT> getReplyedSuggestion() {
        return messageTMapper.selectSuggestionReplyed();
    }

    @Override
    public List<MessageT> getNoReplyRecommendation() {
        return null;
    }

    @Override
    public List<MessageT> getReplyedRecommendation() {
        return null;
    }

    @Override
    public List<MessageT> getNoReplySuggestionByUid(Integer uid) {
        return null;
    }

    @Override
    public List<MessageT> getReplyedSuggestionByUid(Integer uid) {
        return null;
    }

    @Override
    public List<MessageT> getNoReplyRecommendationByUid(Integer uid) {
        return null;
    }

    @Override
    public List<MessageT> getReplyedRecommendationByUid(Integer uid) {
        return null;
    }

    @Override
    public List<MessageT> getSuggestion() {
        return messageTMapper.selectSuggestion();
    }

    @Override
    public List<MessageT> getRecommendation() {
        return messageTMapper.selectRecommendation();
    }

    @Override
    public List<MessageT> getSuggestionByUid(Integer uid) {
        return messageTMapper.selectSuggestionByUid(uid);
    }

    @Override
    public List<MessageT> getRecommendationByUid(Integer uid) {
        return messageTMapper.selectRecommendationByUid(uid);
    }
}

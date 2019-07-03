package cn.idevtools.service;

import cn.idevtools.po.MessageT;

import java.util.List;

public interface MessageService {
    int replyMessge(Integer messageId);

    List<MessageT> getNoReplySuggestion();

    List<MessageT> getReplyedSuggestion();

    List<MessageT> getNoReplyRecommendation();

    List<MessageT> getReplyedRecommendation();

    List<MessageT> getNoReplySuggestionByUid(Integer uid);

    List<MessageT> getReplyedSuggestionByUid(Integer uid);

    List<MessageT> getNoReplyRecommendationByUid(Integer uid);

    List<MessageT> getReplyedRecommendationByUid(Integer uid);

    List<MessageT> getSuggestion();
    List<MessageT> getRecommendation();
    List<MessageT> getSuggestionByUid(Integer uid);
    List<MessageT> getRecommendationByUid(Integer uid);
}

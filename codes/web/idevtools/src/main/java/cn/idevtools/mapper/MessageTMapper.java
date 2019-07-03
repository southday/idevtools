package cn.idevtools.mapper;

import cn.idevtools.po.MessageT;
import cn.idevtools.po.RecommendationsT;
import cn.idevtools.po.SuggestionsT;
import io.swagger.models.auth.In;

import java.util.List;

public interface MessageTMapper {

    /**
     * 插入一条意见反馈记录到messge_t表中 southday 2019.03.18
     * @param suggestion
     * @return
     */
    Integer insertSuggestionMsg(SuggestionsT suggestion);

    /**
     * 插入一条工具推荐目录到message_t表中 southday 2019.03.18
     * @param recommendation
     * @return
     */
    Integer insertToolRecommendationMsg(RecommendationsT recommendation);

    List<MessageT> selectRecommendationNoReply();
    List<MessageT> selectSuggestionNoReply();

    List<MessageT> selectRecommendationReplyed();
    List<MessageT> selectSuggestionReplyed();

    List<MessageT> selectRecommendationNoReplyByUid(Integer uid);
    List<MessageT> selectSuggestionNoReplyByUid(Integer uid);

    List<MessageT> selectRecommendationReplyedByUid(Integer uid);
    List<MessageT> selectSuggestionReplyedByUid(Integer uid);

    int setReply(Integer msgId);

    List<MessageT> selectRecommendationByUid(Integer uid);
    List<MessageT> selectSuggestionByUid(Integer uid);
    List<MessageT> selectSuggestion();
    List<MessageT> selectRecommendation();



}
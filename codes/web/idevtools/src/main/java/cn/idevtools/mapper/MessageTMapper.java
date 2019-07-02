package cn.idevtools.mapper;

import cn.idevtools.po.RecommendationsT;
import cn.idevtools.po.SuggestionsT;

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
}
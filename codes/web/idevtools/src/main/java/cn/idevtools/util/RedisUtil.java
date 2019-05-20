package cn.idevtools.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis工具
 * @author 王沁宽
 * @date 2019/5/20
 */
public class RedisUtil {
    @Autowired
    private static RedisTemplate redisTemplate;

    //用户兴趣度上限与下限
    private static int interestHighLimit = 5,interestLowLimit = 0;

    /**
     * 表示用户喜欢某tool
     * @param userId
     * @param toolId
     */
    public static void LikeTool(Integer userId,Integer toolId){
        LikeTool(userId,toolId,1);
    }

    /**
     * 表示用户喜欢某tool
     * @param userId
     * @param toolId
     * @param star 该值越大，表示用户越喜欢
     */
    public static void LikeTool(Integer userId,Integer toolId,int star){
        //生成key
        String key = userId + "::" + toolId;
        //用户兴趣度
        Integer interest;
        //如果key已经存在，则增加用户的兴趣度,注意，用户兴趣度有上限，暂定为5
        if((interest = (Integer)redisTemplate.opsForValue().get(key)) != null){
            if(interest + star <= interestHighLimit){
                redisTemplate.opsForValue().increment(key,star);
            }else {
                redisTemplate.opsForValue().set(key, interestHighLimit);
            }
        } else {
            redisTemplate.opsForValue().set(key,1);
        }
    }

    /**
     * 表示用户不喜欢某tool
     * @param userId
     * @param toolId
     * @param unstar 该值越大，表示用户越不喜欢
     */
    public static void unLikeTool(Integer userId,Integer toolId,int unstar){
        //生成key
        String key = userId + "::" + toolId;
        //用户兴趣度
        Integer interest;
        if((interest = (Integer)redisTemplate.opsForValue().get(key)) != null){
            if(interest - unstar >= interestLowLimit){
                redisTemplate.opsForValue().decrement(key,unstar);
            }else {
                redisTemplate.opsForValue().set(key,interestLowLimit);
            }
        }
    }

    public static void unLikeTool(Integer userId,Integer toolId){
        unLikeTool(userId,toolId,1);
    }
}

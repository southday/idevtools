package cn.idevtools.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;

/**
 * Redis工具
 * @author 王沁宽
 * @date 2019/5/20
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate redisTemplate;

    //用户兴趣度上限与下限
    private static final int interestHighLimit = 5,interestLowLimit = 0;
    /**
     * 表示用户喜欢某tool
     * @param userId
     * @param toolId
     */
    public void LikeTool(Integer userId,Integer toolId){
        LikeTool(userId,toolId,1);
    }

    /**
     * 表示用户喜欢某tool
     * 用户兴趣程度在redis中保存格式为key: userId::toolId ;  value: interest::timeStamp;
     * @param userId
     * @param toolId
     * @param star 该值越大，表示用户越喜欢
     *
     */
    public void LikeTool(Integer userId,Integer toolId,int star){
        //生成key
        String key = userId + "::" + toolId;
        //原始值
        String oldValue;
        //如果key已经存在，则增加用户的兴趣度,注意，用户兴趣度有上限，暂定为5
        if((oldValue = (String) redisTemplate.opsForValue().get(key)) != null){
            int interest = Integer.valueOf(oldValue.split("::")[0]);
            if(interest + star <= interestHighLimit){
                int newInterest = interest + star;
                redisTemplate.opsForValue().set(key,newInterest + "::" + System.currentTimeMillis());
            }else {
                redisTemplate.opsForValue().set(key, interestHighLimit + "::" +System.currentTimeMillis());
            }
        } else {
            redisTemplate.opsForValue().set(key,1 + "::" + System.currentTimeMillis());
        }
    }

    /**
     * 表示用户不喜欢某tool
     * @param userId
     * @param toolId
     * @param unstar 该值越大，表示用户越不喜欢
     */
    public void unLikeTool(Integer userId,Integer toolId,int unstar){
        //生成key
        String key = userId + "::" + toolId;
        //原始值
        String oldValue;
        //如果key已经存在，则增加用户的兴趣度,注意，用户兴趣度有上限，暂定为5
        if((oldValue = (String) redisTemplate.opsForValue().get(key)) != null){
            int interest = Integer.valueOf(oldValue.split("::")[0]);
            if(interest - unstar >= interestLowLimit){
                int newInterest = interest - unstar;
                redisTemplate.opsForValue().set(key,newInterest + "::" + System.currentTimeMillis());
            }else {
                redisTemplate.opsForValue().set(key, interestLowLimit + "::" +System.currentTimeMillis());
            }
        }
    }

    public void unLikeTool(Integer userId,Integer toolId){
        unLikeTool(userId,toolId,1);
    }

    /**
     * 获得匹配pattern的所有key value
     * @param pattern
     * @return
     */
    public HashMap<String,String> getKeys(String pattern){
        Set<String> keys = redisTemplate.keys(pattern);
        HashMap<String,String> map = new HashMap<>();
        for(String key : keys){
            String value = String.valueOf(redisTemplate.opsForValue().get(key));
            map.put(key,value);
        }
        return map;
    }

    /**
     * 获得用户兴趣键值对(正则待完善)
     * @return
     */
    public HashMap<String,String> getInterestKeys(){
        return getKeys("*::*");
    }
}

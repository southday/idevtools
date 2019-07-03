package cn.idevtools.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;



/**
 * 用于完成推荐(单独开线程完成计算、推荐)
 * @author 王沁宽
 * @date 2019/5/20
 */
@Component
public class Recommend implements ApplicationContextAware {

    private static final Logger logger = LogManager.getLogger(Recommend.class);

    //进行推荐的对象
    public static GenericItemBasedRecommender recommender = null;

    @Autowired
    RecommendThread recommendThread;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Thread recommenThread = new Thread(recommendThread);
        recommenThread.start();
    }


}



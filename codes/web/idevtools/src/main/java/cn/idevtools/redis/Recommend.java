package cn.idevtools.redis;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * 用于完成推荐(单独开线程完成计算、推荐)
 * @author 王沁宽
 * @date 2019/5/20
 */
@Component
public class Recommend implements ApplicationContextAware {

    private static final Logger logger = LogManager.getLogger(Recommend.class);
    //用于分析的文件路径
    public static String dataPath = Recommend.class.getClassLoader().getResource("/data/ratings.dat").getPath();
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Thread recommenThread = new Thread(new RecommendThread());
        recommenThread.start();
    }

}

class RecommendThread implements Runnable{
    @Override
    public void run() {


    }
}

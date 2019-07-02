package cn.idevtools.redis;

import cn.idevtools.common.annotation.PrintExecTime;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * 推荐算法线程(单独开线程完成计算、推荐)
 * @author 王沁宽
 * @date 2019/5/21
 */
@Component
public class RecommendThread implements Runnable{
    private static final Logger logger = LogManager.getLogger(RecommendThread.class);

    //进行推荐算法计算的时间间隔
    private static final long interval = 60*1000;
    //用于分析的文件路径 如果涉及到mvc的测试，要把这句注释掉
    //public static final String dataPath = Recommend.class.getClassLoader().getResource("/data/ratings.dat").getPath();
    public static final String dataPath = "E:\\ratings.dat";

    @Autowired
    RedisUtil redisUtil;
    //生成数据文件
    @PrintExecTime
    private void createDataFile() throws Exception{
        FileOutputStream out;
        BufferedWriter b = null;
        out = new FileOutputStream(new File(dataPath));
        b = new BufferedWriter(new OutputStreamWriter(out));

        HashMap<String,String> data = redisUtil.getInterestKeys();
        for (Map.Entry<String,String> entry : data.entrySet()) {
            b.write(entry.getKey()+"::"+entry.getValue()+"\n");
        }
        b.close();
        out.close();
    }

    @PrintExecTime
    private void recommend() throws Exception {
        //准备数据 这里是网上下的电影评分数据
        File file = new File(dataPath);
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = new GroupLensDataModel(file);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        Recommend.recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
    }


    @Override
    public void run() {
        while(true){
            try {
                //生成数据文件
                createDataFile();
                //执行推荐算法
                recommend();
            }catch (Exception e){
                if (!"dataFile is empty".equals(e.getMessage()))
                    logger.warn(e);
            }
            try {
                Thread.sleep(interval);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}

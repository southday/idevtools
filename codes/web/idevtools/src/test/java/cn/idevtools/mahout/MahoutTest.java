package cn.idevtools.mahout;

import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;
import org.apache.mahout.cf.taste.similarity.precompute.example.GroupLensDataModel;
import org.junit.Test;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;


import java.io.File;
import java.util.List;

/**
 * 推荐系统测试
 */
public class MahoutTest {
    @Test
    public void mahoutTest() throws Exception{
        //准备数据 这里是网上下的电影评分数据
        File file = new File("E:\\ratings.dat");
        //将数据加载到内存中，GroupLensDataModel是针对开放电影评论数据的
        DataModel dataModel = new GroupLensDataModel(file);
        //计算相似度，相似度算法有很多种，欧几里得、皮尔逊等等。
        ItemSimilarity itemSimilarity = new PearsonCorrelationSimilarity(dataModel);
        //构建推荐器，协同过滤推荐有两种，分别是基于用户的和基于物品的，这里使用基于物品的协同过滤推荐
        GenericItemBasedRecommender recommender = new GenericItemBasedRecommender(dataModel, itemSimilarity);
        //给用户ID等于5的用户推荐10个与2398相似的商品
        List<RecommendedItem> recommendedItemList = recommender.recommendedBecause(123,212,1);
        //打印推荐的结果
        System.out.println("使用基于物品的协同过滤算法");
        System.out.println("根据用户5当前浏览的商品2398，推荐10个相似的商品");
        for (RecommendedItem recommendedItem : recommendedItemList) {
            System.out.println(recommendedItem);
        }
//        long start = System.currentTimeMillis();
//        recommendedItemList = recommender.recommendedBecause(5, 34, 10);
//        //打印推荐的结果
//        System.out.println("使用基于物品的协同过滤算法");
//        System.out.println("根据用户5当前浏览的商品34，推荐10个相似的商品");
//        for (RecommendedItem recommendedItem : recommendedItemList) {
//            System.out.println(recommendedItem);
//        }
//        System.out.println(System.currentTimeMillis() -start);
    }
}

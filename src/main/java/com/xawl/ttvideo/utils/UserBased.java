package com.xawl.ttvideo.utils;

import com.xawl.ttvideo.pojo.UserRecommend;
import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.*;
import org.apache.mahout.cf.taste.impl.recommender.*;
import org.apache.mahout.cf.taste.impl.similarity.*;
import org.apache.mahout.cf.taste.model.*;
import org.apache.mahout.cf.taste.recommender.*;
import org.apache.mahout.cf.taste.similarity.*;
import org.junit.Test;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.util.*;


public class UserBased {

    final static int NEIGHBORHOOD_NUM = 50;
    final static int RECOMMENDER_NUM = 5;

    public static List<UserRecommend> calculation() throws IOException, TasteException {
        String aStatic = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName = aStatic + "/" + "UserRecommend.cvs";
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        DataModel model = new FileDataModel(file);
        UserSimilarity user = new EuclideanDistanceSimilarity(model);
        NearestNUserNeighborhood neighbor = new NearestNUserNeighborhood(NEIGHBORHOOD_NUM, user, model);
        Recommender r = new GenericUserBasedRecommender(model, neighbor, user);
        LongPrimitiveIterator iter = model.getUserIDs();
        List<UserRecommend> result = new ArrayList<>();
        UserRecommend userRecommend;
        StringBuilder sb = new StringBuilder();
        while (iter.hasNext()) {
            long uid = iter.nextLong();
            userRecommend = new UserRecommend();
            userRecommend.setUid(Integer.parseInt(uid + ""));
            List<RecommendedItem> list = r.recommend(uid, RECOMMENDER_NUM);
            for (RecommendedItem ritem : list) {
                sb.append(ritem.getItemID() + ",");
            }
            if (sb.length() > 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
            userRecommend.setCids(sb.toString());
            sb.delete(0, sb.length());
            result.add(userRecommend);
        }
        return result;
    }

    public static boolean writeToUserRecommendLine(Integer uid, Integer cid, String score) {
        String aStatic = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        String fileName = aStatic + "/" + "UserRecommend.cvs";
        File file = new File(fileName);
        BufferedWriter nw = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            nw = new BufferedWriter(new FileWriter(file, true));
            nw.write(uid + "," + cid + "," + score + "\n");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (nw != null) {
                try {
                    nw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return true;
    }

    @Test
    public void test() throws IOException, TasteException {
        new UserBased().writeToUserRecommendLine(3, 103, 4.0 + "");
        List<UserRecommend> calculation = new UserBased().calculation();
        for (UserRecommend u : calculation) {
            System.out.println(u);
        }
    }
}
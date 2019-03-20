package com.xawl.ttvideo.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

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

import java.io.*;
import java.util.*;


public class UserBased {

    final static int NEIGHBORHOOD_NUM = 2;
    final static int RECOMMENDER_NUM = 3;

    public List<UserRecommend> calculation() throws IOException, TasteException {
        String file = "D:\\test.cvs";
        DataModel model = new FileDataModel(new File(file));
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
/*
                System.out.printf("(%s,%f)", ritem.getItemID(), ritem.getValue());*/
            }
            if (sb.length() > 1) {
                sb.delete(sb.length() - 1, sb.length());
            }
            userRecommend.setCids(sb.toString());
            sb.delete(0, sb.length());
            result.add(userRecommend);
            /* System.out.println();*/
        }
        return result;
    }

    @Test
    public void test() throws IOException, TasteException {
        List<UserRecommend> calculation = new UserBased().calculation();
    }
}
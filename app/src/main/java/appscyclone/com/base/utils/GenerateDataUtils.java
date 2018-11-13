package appscyclone.com.base.utils;
/*
 * Created by HoangDong on 20/09/2017.
 */



import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import appscyclone.com.base.data.local.ExpandModel;

public class GenerateDataUtils {
    public static List<ExpandModel> genDataExpandRecyclerView() {
        List<ExpandModel> result = new ArrayList<>();
        Random random = new Random();
        //int max = random.nextInt(20);
        int max = 20;
        for (int i = 0; i < max; i++) {
            List<String> child = new ArrayList<>();
            //int maxChild = random.nextInt(10);
            int maxChild = 10;
            for (int j = 0; j < maxChild; j++) {
                child.add("Child Number: " + random.nextInt(100));
            }
            ExpandModel expandModel = new ExpandModel(String.valueOf(i), child);
            result.add(expandModel);
        }
        return result;
    }

    public static List<String> genListString(int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < size; i++)
            result.add("String Number " + i);
        return result;
    }
}

package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 12.10.2016.
 */
public class RandomGenerator extends ParentSorter {
//    public RandomGenerator(int count) {
//        this.count = count;
//    }
//    private int count, yShift, frameWidth, frameHeight, rX;
//        private ArrayList<Integer> randomList;

//    public ArrayList<Integer> getRandomList(int count) {
//        ArrayList<Integer> randomList = new ArrayList<>(count);
//        for (int i = 0; i < count; i++) {
//            randomList.add(i);
//        }
//        Collections.shuffle(randomList);
//        return randomList;
//    }

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        if (j == 0 && transit == 0) {
            transit++;
            return randomList;
        }
        Collections.shuffle(randomList);
        return randomList;
    }

}

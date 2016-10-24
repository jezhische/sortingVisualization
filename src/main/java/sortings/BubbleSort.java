package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort extends ParentSorter {

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
//        if (j == 0 && transit == 0) {
//            transit++;
//            return randomList;
//        } else
        if (j < k) {
            if (randomList.get(j) > randomList.get(j + 1)) {
                Collections.swap(randomList, j, j + 1);
                j++;
            } else if (randomList.get(j) < randomList.get(j + 1))
                j++;

        } else if (j == k) {
            k--;
            j = 0;
            if (randomList.get(j) > randomList.get(j + 1)) {
                Collections.swap(randomList, j, j + 1);
                j++;
            } else if (randomList.get(j) < randomList.get(j + 1))
                j++;
        }
        return randomList;
    }
}

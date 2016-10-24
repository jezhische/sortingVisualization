package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 22.10.2016.
 */
public class QuickSort extends ParentSorter {

    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        if (j < k) {
            if (randomList.get(j) > randomList.get(k)) {
                Collections.swap(randomList, j, k);
                k--;
            } else if (randomList.get(j) < randomList.get(j + 1))
                j++;

        } else if (j == k) {}

        return randomList;
    }
}

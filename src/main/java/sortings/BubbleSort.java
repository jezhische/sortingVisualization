package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort extends ParentSorter {
    public BubbleSort() {} // TODO: убрать, когда уберу вспомогательные классы
//    ArrayList<Integer> randomList = new ArrayList<>();
    public BubbleSort(ArrayList<Integer> randomList) {
        this.randomList = randomList;
        j = 0;
        k = randomList.size() - 1;
    }
    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
//        if (j == 0 && transit == 0) {
//            transit++;
//            return randomList;
//        } else
        this.randomList = randomList;
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

    public void reset() {
        j = 0;
        k = randomList.size() - 1;
    }
}

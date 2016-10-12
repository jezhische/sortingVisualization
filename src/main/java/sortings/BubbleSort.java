package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort {

    public static void sort(ArrayList<Integer> series) {
        Collections.shuffle(series);
        for (int k = series.size(); k >= 0; k--) {
            for (int j = 0; j < k - 1; j++) {
                if (series.get(j) > series.get(j + 1)) {
                    Collections.swap(series, j, j + 1);
                }
            }
        }
    }
}

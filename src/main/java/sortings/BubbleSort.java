package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort {

    public static ArrayList<Integer> sort(ArrayList<Integer> series) {
        Collections.shuffle(series);
        for (int k = series.size() - 1; k > 0; k--) {
            for (int j = 0; j < k; j++) {
                if (series.get(j) > series.get(j + 1)) {
                    Collections.swap(series, j, j + 1);
                }
            }
        }
        return series;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(4);
        arr.add(12);
        arr.add(1);
        arr.add(8);
        arr.add(6);

        for (int x: sort(arr)) {
            System.out.println(x);
        }
    }
}

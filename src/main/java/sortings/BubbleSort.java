package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort {
    private int count = 200;
    private ArrayList<Integer> series;
    private int j;
    private int k;

    public BubbleSort(int count) {
        this.count = count;
    }

    private ArrayList<Integer> getRandomList(int count) {
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            randomList.add(i);
        }
        Collections.shuffle(randomList);
        return randomList;
    }

    {
        series = getRandomList(count);
        j = 0;
        k = series.size() - 1;
    }

    public ArrayList<Integer> sort() {
//        series = getRandomList(count);
        if (j < k) {
            if (series.get(j) > series.get(j + 1)) {
                Collections.swap(series, j, j + 1);
                j++;
//                return series;
            } else if (j == k) {
                k--;
                j = 0;
                if (series.get(j) > series.get(j + 1)) {
                    Collections.swap(series, j, j + 1);
                    j++;
//                    return series;
                }
            }
        }
//        else if (k == 1)
//            series = series;
//            return series;
        return series;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> arr = new BubbleSort(25).sort();
//
//        for (int x : arr) {
//            System.out.print(x + ", ");
//        }
//    }
}

package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort {
    private int count;
    //    private ArrayList<Integer> series;
    public int j;
    public int k;

    public BubbleSort(int count) {
        this.count = count;
    }

//    private ArrayList<Integer> getRandomList() {
//        ArrayList<Integer> randomList = new ArrayList<>();
//        for (int i = 0; i < count; i++) {
//            randomList.add(i);
//        }
//        Collections.shuffle(randomList);
//        return randomList;
//    }

    {
        j = 0;
        k = count - 1;
    }
    public int transit = 0;

    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
//        series = getRandomList(count);
        if (j == 0 && transit == 0) {
            transit++;
            return randomList;
        } else if (j < k) {
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
//        else if (k == 0)
////            series = series;
//            return randomList;
        return randomList;
    }

//    public static void main(String[] args) {
//        ArrayList<Integer> arr = new BubbleSort(25).sort();
//
//        for (int x : arr) {
//            System.out.print(x + ", ");
//        }
//    }
}

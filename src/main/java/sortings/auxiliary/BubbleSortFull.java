package sortings.auxiliary;

import sortings.ParentSorter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 22.10.2016.
 */
public class BubbleSortFull extends ParentSorter {

    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        k = randomList.size();

        for(int i = k; i > 0; i-- ) {
            for (int n = j; n < k - 1; n++ ) {
                if (randomList.get(n) > randomList.get(n + 1))
                    Collections.swap(randomList, n, n + 1);
            }
        }
        return randomList;
    }

    private ArrayList<Integer> whileSort(ArrayList<Integer> randomList) {
        k = randomList.size();
        while (k > 0) {
            while (j < k - 1) {
                if (randomList.get(j) > randomList.get(j + 1))
                    Collections.swap(randomList, j, j + 1);
                j++;
            }
            k--;
        }
        return randomList;
    }

    private ArrayList<Integer> getRandomList(int count) {
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            randomList.add(i);
        }
        Collections.shuffle(randomList);
        return randomList;
    }

    private void printArray (ArrayList<Integer> list) {
        for (Integer arr: list)
            System.out.print(arr + ", ");
    }

    public static void main(String[] args) {
        BubbleSortFull bubble = new BubbleSortFull();
        ArrayList<Integer> list = bubble.getRandomList(20);
        bubble.printArray(list);
        System.out.println("");
        bubble.printArray(bubble.sort(list));
        System.out.println("");
        bubble.printArray(bubble.whileSort(list));
    }
}

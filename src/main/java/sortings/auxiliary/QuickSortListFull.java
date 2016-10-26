package sortings.auxiliary;

import sortings.ParentSorter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 24.10.2016.
 * начиналось все с: https://dev64.wordpress.com/2013/07/24/quick-sort/
 */
public class QuickSortListFull extends ParentSorter {
    private int count;

    private void quickSort(ArrayList<Integer> randomList, int left, int right) {
        int leftIndex, rightIndex;
        leftIndex = left;
        rightIndex = right;
        int pivot = randomList.get(leftIndex + (rightIndex - leftIndex) / 2);
//        int pivot = randomList.get(new Random().nextInt(randomList.size())); // что интересно, со случайным pivot метод часто
// //срабатывает с очень явно видной задержкой, работает до count = 29, дальше повисает, а еще дальше выдает стековерфлоу.
        while (leftIndex <= rightIndex) {
            while (randomList.get(leftIndex) < pivot)
                leftIndex++;
            while (randomList.get(rightIndex) > pivot)
                rightIndex--;

            if (leftIndex < rightIndex) {
                Collections.swap(randomList, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            } else if (leftIndex == rightIndex) {  // условие "==" здесь нужно для того, чтобы после leftIndex++;
                // и rightIndex--; получилось leftIndex > rightIndex, и произошел бы выход из внешнего цикла
                leftIndex++;
                rightIndex--;
            }
        }

        if (left < leftIndex - 1) // условие определения левого subarray
            quickSort(randomList, left, leftIndex - 1);
        if (leftIndex < right) // условие определения правого subarray
            quickSort(randomList, leftIndex, right);
    }

    private ArrayList<Integer> getRandomList() {
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++)
            randomList.add(i);
        Collections.shuffle(randomList);
        return randomList;
    }

    private void printList(int count) {
        this.count = count;
        ArrayList<Integer> randomList = getRandomList();
        System.out.print("randomList = " + randomList);
        System.out.println("");
        sort(randomList);
        System.out.print("randomList = " + randomList);
    }

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        k = randomList.size() - 1;
//        k = count - 1;
        quickSort(randomList, j, k);
        return randomList;
    }

    public static void main(String[] args) {
        new QuickSortListFull().printList(25);
//        System.out.println(new Random().nextInt(5));
    }
}

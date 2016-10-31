package sortings.auxiliary;

import sortings.ParentSorter;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 24.10.2016.
 * начиналось все с: https://dev64.wordpress.com/2013/07/24/quick-sort/
 */
public class QuickSortListFullWithoutIntPar extends ParentSorter {
    private int count;

    public QuickSortListFullWithoutIntPar(int count) {
        this.count = count;
    }

    ArrayList<Integer> randomList = new ArrayList<>();
    private int left, right;

    public QuickSortListFullWithoutIntPar(ArrayList<Integer> randomList) {
        this.randomList = randomList;
        left = 0;
        right = randomList.size() - 1;
    }

    public void quickSort() {
        int leftIndex, rightIndex;

//        this.randomList = randomList;
        leftIndex = left;
        rightIndex = right;
        int pivotIndex = left + (right - left) / 2;
        int pivot = randomList.get(pivotIndex);
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
        System.out.println(", pivot index = " + pivotIndex + ", pivot = " + pivot + ", leftIndex =" + leftIndex +
                ", rightIndex =" + rightIndex + ", left = " + left + ", right = " + right);

        int temp = right;
        if (left < leftIndex - 1) { // условие определения левого subarray
//            right = leftIndex - 1;
            System.out.print("left subarray: ");
            right = rightIndex + 1;
            quickSort();
        }
        if (leftIndex < temp) { // условие определения правого subarray
            System.out.print("right subarray: ");
            left = leftIndex;
            right = temp;
            quickSort();
        }
//        quickSort(randomList, rightIndex + 1, right); // - можно и так
    }

    private ArrayList<Integer> getRandomList() {
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++)
            randomList.add(i);
        Collections.shuffle(randomList);
        return randomList;
    }

    private void printList() {
        System.out.print("randomList = " + randomList);
        System.out.println("");
        sort();
        System.out.print("randomList = " + randomList);
    }

    @Override
    public ArrayList<Integer> sort() {
        quickSort();
        return randomList;
    }

    public static void main(String[] args) {
        new QuickSortListFullWithoutIntPar(new QuickSortListFullWithoutIntPar(9).getRandomList()).printList();
    }
}

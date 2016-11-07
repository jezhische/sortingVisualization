package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 24.10.2016.
 * начиналось все с: https://dev64.wordpress.com/2013/07/24/quick-sort/
 */
public class QuickSort02 extends ParentSorter {

    ArrayList<Integer> randomList = new ArrayList<>();
//    private int left, right, leftIndex, rightIndex;
//    public int pivotIndex;

    public QuickSort02(ArrayList<Integer> randomList) {
        this.randomList = randomList;
        left = 0;
        right = randomList.size() - 1;

    }

//    public QuickSort() {
//        left = 0;
//        right = randomList.size() - 1;
//    }

    boolean pulse = true;

    public void quickSort() {
//        if (right >= left)
//            return;
//        int leftIndex, rightIndex;
        if (pulse) {
            leftIndex = left;
            rightIndex = right;
            pivotIndex = left + (right - left) / 2;
            pivot = randomList.get(pivotIndex);
        }
        pulse = false;
//        int pivotIndex = leftIndex + (rightIndex - leftIndex) / 2;
        System.out.println("leftIndex before = " + leftIndex + ", rightIndex before = " + rightIndex +
                ", pivotIndex before =" + pivotIndex);
        if (leftIndex < pivotIndex) {
            if (randomList.get(leftIndex) <= pivot) { // как только условие не выполнится, leftIndex перестанет
                // увеличиваться и работа перейдет к правому сабэррею (следующее условие).
                leftIndex++;
                System.out.println("marker 1: leftIndex = " + leftIndex);
                return;
            }
        } else if (rightIndex > pivotIndex) {
            if (randomList.get(rightIndex) >= pivot) { // как только условие не выполнится, rightIndex перестанет
                // уменьшаться и произойдет swap значений (следующее условие).
                rightIndex--;
                System.out.println("marker 2: rightIndex = " + rightIndex);
                return;
            }
        }
        if (leftIndex < rightIndex) {
            Collections.swap(randomList, leftIndex, rightIndex);
            if (leftIndex < pivotIndex)
                leftIndex++;
            if (rightIndex > pivotIndex)
                rightIndex--;
            return;
        } else if(leftIndex==rightIndex) {
        int rTemp = right;
        int lTemp = left;
        if (left < leftIndex) { // условие определения левого subarray
            left = lTemp;
            right = rightIndex;
//                right = leftIndex - 1;
            pulse = true;
            quickSort();
            return;
        }
        if (leftIndex < rTemp) { // условие определения правого subarray
            left = leftIndex;
            right = rTemp;
            pulse = true;
            quickSort();
        }
    }
//        System.out.println(", pivot index = " + pivotIndex + ", pivot = " + pivot + ", leftIndex =" + leftIndex +
//                ", rightIndex =" + rightIndex + ", left = " + left + ", right = " + right);
}

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
//        this.randomList = randomList;
        quickSort();
        return randomList;
    }

    public void reset() {
        left = 0;
        right = randomList.size() - 1;
    }
}

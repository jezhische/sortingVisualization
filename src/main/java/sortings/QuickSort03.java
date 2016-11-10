package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 24.10.2016.
 * начиналось все с: https://dev64.wordpress.com/2013/07/24/quick-sort/
 */
public class QuickSort03 extends ParentSorter {

//    ArrayList<Integer> randomList = new ArrayList<>();
//    private int left, right, leftIndex, rightIndex;
//    public int pivotIndex;

    public QuickSort03(ArrayList<Integer> randomList) {
        this.randomList = randomList;
        pulse = true;
//        left = 0;
//        right = randomList.size() - 1;

    }

//    public QuickSort() {
//        left = 0;
//        right = randomList.size() - 1;
//    }



    private void quickSort(int left, int right) {
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
        if (leftIndex < pivotIndex - 1 && randomList.get(leftIndex) <= pivot) {
             // как только условие не выполнится, leftIndex перестанет
                // увеличиваться и работа перейдет к правому сабэррею (следующее условие).
                leftIndex++;
                System.out.println("marker 1: leftIndex = " + leftIndex);
                return;
        } else if (rightIndex > pivotIndex + 1 && randomList.get(rightIndex) >= pivot) {
             // как только условие не выполнится, rightIndex перестанет
                // уменьшаться и произойдет swap значений (следующее условие).
                rightIndex--;
                System.out.println("marker 2: rightIndex = " + rightIndex);
                return;
        } else if (leftIndex + 1 < rightIndex - 1 && randomList.get(leftIndex) > randomList.get(rightIndex)) {
            Collections.swap(randomList, leftIndex, rightIndex);
            if (leftIndex < pivotIndex - 1)
                leftIndex++;
            if (rightIndex > pivotIndex + 1)
                rightIndex--;
            return;
        }
        else if (leftIndex + 1 < rightIndex - 1 && randomList.get(leftIndex) <= randomList.get(rightIndex)) {
            if (leftIndex < pivotIndex - 1)
                leftIndex++;
            if (rightIndex > pivotIndex + 1)
                rightIndex--;
            return;
        }
        else if(leftIndex == rightIndex - 2) {
        int rTemp = right;
        int lTemp = left;
        if (left < leftIndex && pivotIndex != 0) { // условие определения левого subarray
//            left = lTemp;
            right = rightIndex;
//                right = leftIndex - 1;
            pulse = true;
            quickSort(left, right);
            return;
        }
        if (leftIndex < rTemp && pivotIndex != 0) { // условие определения правого subarray
            left = leftIndex;
            right = rTemp;
            pulse = true;
            quickSort(left, right);
        }
    }
//        System.out.println(", pivot index = " + pivotIndex + ", pivot = " + pivot + ", leftIndex =" + leftIndex +
//                ", rightIndex =" + rightIndex + ", left = " + left + ", right = " + right);
}

    @Override
    public ArrayList<Integer> sort() {
//        this.randomList = randomList;
        quickSort(0, randomList.size() - 1);
        return randomList;
    }

    public void reset(ArrayList<Integer> randomList) {
        this.randomList = randomList;
        left = 0;
        right = randomList.size() - 1;
        pulse = true;
    }
}

package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 24.10.2016.
 * начиналось все с: https://dev64.wordpress.com/2013/07/24/quick-sort/
 */
public class QuickSort extends ParentSorter {

    public QuickSort() {
        randomList = new ArrayList<>();
        leftIndex = 0;
        rightIndex = randomList.size() - 1;
    }
    ArrayList<Integer> randomList;

    public void quickSort(ArrayList<Integer> randomList, int left, int right) {
//        if (right >= left)
//            return;
//        int leftIndex, rightIndex;
        leftIndex = left;
        rightIndex = right;
//        int pivotIndex = leftIndex + (rightIndex - leftIndex) / 2;
        int pivotIndex = left + (right - left) / 2;
        pivot = randomList.get(pivotIndex);
        System.out.println("leftIndex before = " + leftIndex + ", rightIndex before = " + rightIndex +
                ", pivotIndex before =" + pivotIndex);
        if (leftIndex <= rightIndex) {
            if (randomList.get(leftIndex) < pivot) {
                leftIndex++;
//                return;
            } else if (randomList.get(leftIndex) >= pivot) {
                if (randomList.get(rightIndex) > pivot) {
                    rightIndex--;
//                    return;
                } else if (randomList.get(rightIndex) <= pivot) {
                    if (leftIndex < rightIndex) {
                        Collections.swap(randomList, leftIndex, rightIndex);
                        leftIndex++;
                        rightIndex--;
//                        return;
                    } else if (leftIndex == rightIndex) {  // условие "==" здесь нужно для того, чтобы после leftIndex++;
                        // и rightIndex--; получилось leftIndex > rightIndex, и произошел бы выход из внешнего цикла
                        leftIndex++;
                        rightIndex--;
//                        return;
                    }
                }
            }
        } else if (leftIndex > rightIndex) {
            if (left < leftIndex - 1) { // условие определения левого subarray
                right = leftIndex - 1;
                quickSort(randomList, left, leftIndex - 1);
            }
            if (leftIndex < right) { // условие определения правого subarray
                left = leftIndex;
                quickSort(randomList, leftIndex, right);
            }
        }
        System.out.println("leftIndex after = " + leftIndex + ", rightIndex after = " + rightIndex +
                ", pivotIndex after =" + pivotIndex);
//        jjj = leftIndex;
//        kkk = rightIndex;
    }

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
//        int right = randomList.size() - 1;
//        int left = 0;
        quickSort(randomList, 0, randomList.size() - 1);
        return randomList;
    }

//    public static void main(String[] args) {
//        new QuickSort().printList(25);
////        System.out.println(new Random().nextInt(5));
//    }
}

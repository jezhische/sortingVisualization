package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 24.10.2016.
 * начиналось все с: https://dev64.wordpress.com/2013/07/24/quick-sort/
 */
public class QuickSort extends ParentSorter {
    public int jjj, kkk;

    public void quickSort(ArrayList<Integer> randomList, int left, int right) {
//        int leftIndex, rightIndex;
            leftIndex = left;
            rightIndex = right;
            int pivot = randomList.get(leftIndex + (rightIndex - leftIndex) / 2);
        System.out.println("leftIndex before = " + leftIndex + ", rightIndex before = " + rightIndex);
//        int pivot = randomList.get(new Random().nextInt(randomList.size())); // что интересно, со случайным pivot метод часто
// //срабатывает с очень явно видной задержкой, работает до count = 29, дальше повисает, а еще дальше выдает стековерфлоу.
            if (leftIndex <= rightIndex) {
                if (randomList.get(leftIndex) < pivot) {
                    leftIndex++;
                } else if (randomList.get(leftIndex) >= pivot) {
                    if (randomList.get(rightIndex) > pivot) {
                        rightIndex--;
                    } else if (randomList.get(rightIndex) <= pivot) {
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
                }
        }
        else if (leftIndex > rightIndex) {
            if (left < leftIndex - 1) // условие определения левого subarray
                quickSort(randomList, left, leftIndex - 1);
            if (leftIndex < right) // условие определения правого subarray
                quickSort(randomList, leftIndex, right);
        }
        System.out.println("leftIndex after = " + leftIndex + ", rightIndex after = " + rightIndex);
//        jjj = leftIndex;
//        kkk = rightIndex;
    }

//    private ArrayList<Integer> getRandomList() {
//        ArrayList<Integer> randomList = new ArrayList<>(count);
//        for (int i = 0; i < count; i++)
//            randomList.add(i);
//        Collections.shuffle(randomList);
//        return randomList;
//    }
//
//    private void printList(int count) {
//        this.count = count;
//        ArrayList<Integer> randomList = getRandomList();
//        System.out.print("randomList = " + randomList);
//        System.out.println("");
//        sort(randomList);
//        System.out.print("randomList = " + randomList);
//    }

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

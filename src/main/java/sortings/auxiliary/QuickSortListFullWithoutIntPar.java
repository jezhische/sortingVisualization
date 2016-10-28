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
    ArrayList<Integer> randomList = new ArrayList<>();

    public QuickSortListFullWithoutIntPar(ArrayList<Integer> randomList) {
        this.randomList = randomList;
        left = 0;
        right = randomList.size() - 1;
    }

    public void quickSort() {
//        int leftIndex, rightIndex;

//        this.randomList = randomList;
        leftIndex = left;
        rightIndex = right;
        int pivotIndex = left + (right - left) / 2;
        int pivot = randomList.get(pivotIndex);
        System.out.print("pivot index before = " + pivotIndex);
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
        System.out.println(", pivot index after = " + pivotIndex + ", leftIndex after =" + leftIndex + ", rightIndex after =" + rightIndex);

        int temp = right;
        if (left < leftIndex - 1) { // условие определения левого subarray
//            right = leftIndex - 1;
            System.out.print("left subarray: ");
            right = rightIndex + 1;
            quickSort();
        }
        if (leftIndex < right) { // условие определения правого subarray
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
//        this.count = count;
//        ArrayList<Integer> randomList = getRandomList();
        System.out.print("randomList = " + randomList);
        System.out.println("");
        sort(randomList);
        System.out.print("randomList = " + randomList);
    }

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        k = randomList.size() - 1;
//        k = count - 1;
        quickSort();
        return randomList;
    }

    public static void main(String[] args) {
        QuickSortListFull qTemp = new QuickSortListFull();
        qTemp.count = 25;
        ArrayList<Integer> randomList = qTemp.getRandomList();
        new QuickSortListFullWithoutIntPar(randomList).printList();
//        System.out.println(new Random().nextInt(5));
    }
}

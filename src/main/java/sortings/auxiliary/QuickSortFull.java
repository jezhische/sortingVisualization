package sortings.auxiliary;

import sortings.ParentSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Ежище on 23.10.2016.
 */
public class QuickSortFull extends ParentSorter {
    int count; // размер списка randomList.size()
//    int pivot = new Random().nextInt(count); // это всегда будет число в диапазоне от 0 до (count - 1), т.е. годится как
//    // индекс случайного элемента списка

    public List<Integer> qSort(List<Integer> randomList) {
        int pivotAtSortedPosition;
        int left = 0;
        int right = count - 1;
        // j = 0 согласно инициализации в родительском классе ParentSorter
//        k = randomList.size() - 1; // другими словами, k = count - 1;
//        int x = randomList.get(pivot);
        int x = randomList.get(left + (right - left) / 2);
        while (left < right) {
            while (randomList.get(left) < x) // находим элемент слева от x, который больше, чем x, и, соответственно,
                // должен находиться справа от него - нашли и остановились, поскольку перестало выполняться
                // условие (randomList.get(j) < x)
                left++;
            while (randomList.get(right) > x) // находим элемент справа от x, который меньше, чем x, и, соответственно,
                // должен находиться слева от него - нашли и остановились
                right--;
//            if (j < k) //и если элемент, больший чем x, находится слева от x, а элемент, меньший чем x, находится справа
                Collections.swap(randomList, left, right); // то меняем их местами
            // и если j < k, то продолжаем выполнять цикл, пока не случится j = k
        }
        System.out.println("x = " + x);

        if (x > 0)
            qSort(randomList.subList(0, x - 1));
        if (x < randomList.size())
            qSort(randomList.subList(x, randomList.size()));

//        // pivot равен j-му элементу (изначально левому 0-му):
//        while (j < k)
//            if (randomList.get(j) > randomList.get(k)) {
//                Collections.swap(randomList, j, k);
//                pivotAtSortedPosition = k;
//                k = j;
//                j = pivotAtSortedPosition;
//            } else if (randomList.get(j) < randomList.get(k))
//                k--;
//        // если 0-ой элемент был самым маленьким и после всех декрементов k = 0 (точнее, k = j), то смещаем pivot :
//        if (k == j) {
//            j++;
//            k = randomList.size() - 1;
//            qSort(randomList);
//        }
        return randomList;
    }

    private List<Integer> getRandomList() {
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++)
            randomList.add(i);
        Collections.shuffle(randomList);
        return (List<Integer>) randomList;
    }

    private void printList(int count) {
        this.count = count;
        List<Integer> randomList = getRandomList();
        System.out.print("randomList = " + randomList);
//        System.out.printf("  j = %d, k = %d", j, count - 1);
        System.out.println("");
        qSort(randomList);
        System.out.print("randomList = " + randomList);
//        System.out.printf("  j = %d, k = %d", j, k);
        qSort(randomList);
    }

    public static void main(String[] args) {
        new QuickSortFull().printList(9);
//        System.out.println(new Random().nextInt(5));
    }
}

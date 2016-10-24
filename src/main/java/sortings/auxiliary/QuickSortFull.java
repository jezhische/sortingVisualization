package sortings.auxiliary;

import sortings.ParentSorter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by Ежище on 23.10.2016.
 */
public class QuickSortFull extends ParentSorter {
    int count; // размер списка randomList.size()
    int pivot = new Random().nextInt(count); // это всегда будет число в диапазоне от 0 до count - 1, т.е. годится как
    // индекс случайного элемента списка

    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        int pivotAtSortedPosition;
        // j = 0 согласно инициализации в родительском классе ParentSorter
        k = randomList.size() - 1; // другими словами, k = count - 1;
        int x = randomList.get(pivot);
        while (j < k) {
            while (randomList.get(j) < x) // находим элемент слева от x, который больше, чем x, и, соответственно,
                // должен находиться справа от него
                j++;
            while (randomList.get(k) > x) // находим элемент справа от x, который меньше, чем x, и, соответственно,
                // должен находиться слева от него
                k--;
            if (j <= k) //и если элемент, больший чем x, находится слева от x, а элемент, меньший чем x, находится справа
                Collections.swap(randomList, j, k); // то меняем их местами
        }
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
//            sort(randomList);
//        }
        return randomList;
    }

    private ArrayList<Integer> getRandomList() {
        ArrayList<Integer> randomList = new ArrayList<>();
        for (int i = 0; i < count; i++)
            randomList.add(i);
        Collections.shuffle(randomList);
        return randomList;
    }

    private void printList(int count) {
        this.count = count;
        ArrayList<Integer> randomList = getRandomList();
        System.out.print("randomList = " + randomList);
        System.out.printf("  j = %d, k = %d", j, count - 1);
        System.out.println("");
        sort(randomList);
        System.out.print("randomList = " + randomList);
        System.out.printf("  j = %d, k = %d", j, k);
        sort(randomList);
    }

    public static void main(String[] args) {
        new QuickSortFull().printList(5);
//        System.out.println(new Random().nextInt(5));
    }
}

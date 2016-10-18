package sortings;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Ежище on 12.10.2016.
 */
public class BubbleSort extends ParentSorter {
    /** количество элементов - параметр для инстанциирования */
//    private int count;
    /** индекс элемента, который при данной прорисовке сравнивается со следующим в списке (индекс пузырька) */
//    public int j;
    /** индекс элемента, до которого движется пузырек (справа от него все элементы уже отсортированы) */
//    public int k;
    /** чтобы при первой прорисовке списка он не был сразу сортированным на 1 шаг */
    public int transit = 0;

//    public BubbleSort(int count) {
//        this.count = count;
//    }
//    {  // первичная инициализация
//        j = 0;
//        k = count - 1;
//    }

    @Override
    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        if (j == 0 && transit == 0) {
            transit++;
            return randomList;
        } else if (j < k) {
            if (randomList.get(j) > randomList.get(j + 1)) {
                Collections.swap(randomList, j, j + 1);
                j++;
            } else if (randomList.get(j) < randomList.get(j + 1))
                j++;

        } else if (j == k) {
            k--;
            j = 0;
            if (randomList.get(j) > randomList.get(j + 1)) {
                Collections.swap(randomList, j, j + 1);
                j++;
            } else if (randomList.get(j) < randomList.get(j + 1))
                j++;
        }
        return randomList;
    }
}

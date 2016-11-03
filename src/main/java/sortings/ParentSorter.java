package sortings;

import java.util.ArrayList;

/**
 * Created by Ежище on 16.10.2016.
 */
public abstract class ParentSorter {
    /**
     * индекс элемента, который при данной прорисовке сравнивается со следующим в списке (индекс пузырька)
     */
    public int j; // = 0 по умолчанию
    /**
     * индекс элемента, до которого движется пузырек (справа от него все элементы уже отсортированы);
     * при k = count - 1 это индекс конца списка
     */
    public int k;
    /**
     * чтобы при первой прорисовке списка он не был сразу сортированным на 1 шаг
     */
    public int transit = 0;
    //    public int count;
    public ArrayList<Integer> randomList;

    public ArrayList<Integer> sort(ArrayList<Integer> randomList) {
        return randomList;
    }

    public ArrayList<Integer> sort() {
        return randomList;
    }
    public void reset() {}

//    public List<Integer> sort(List<Integer> randomList) {
//        return randomList;
//    }
//
//    public void quickSort(ArrayList<Integer> randomList, int left, int right) {
//    }

//    public void quickSort() {
//
//    }

    public int kkk;
//    public int jjj = 0;
        public int leftIndex, rightIndex, left, right;
//    public int left, right;
    //    {leftIndex = 0;
//    rightIndex = 24;}
    public int pivot;
    public int pivotIndex;
}

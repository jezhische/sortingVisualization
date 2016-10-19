package sortings;

import java.util.ArrayList;

/**
 * Created by Ежище on 16.10.2016.
 */
public abstract class ParentSorter {
    /** индекс элемента, который при данной прорисовке сравнивается со следующим в списке (индекс пузырька) */
    public int j = 0;
    /** индекс элемента, до которого движется пузырек (справа от него все элементы уже отсортированы) */
    public int k;
    /** чтобы при первой прорисовке списка он не был сразу сортированным на 1 шаг */
    public int transit = 0;
//    public int count;
    public ArrayList<Integer> sort(ArrayList<Integer> randomList){return randomList;}
}

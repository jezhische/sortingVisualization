package sortings;

import java.util.ArrayList;

/**
 * Created by Ежище on 16.10.2016.
 */
public abstract class ParentSorter {
    /** индекс элемента, который при данной прорисовке сравнивается со следующим в списке (индекс пузырька) */
    public static int j = 0;
    /** индекс элемента, до которого движется пузырек (справа от него все элементы уже отсортированы) */
    public static int k;
    public ArrayList<Integer> sort(ArrayList<Integer> randomList){return randomList;}
}

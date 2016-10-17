package graphics;

import javax.swing.*;

/**
 * Created by Ежище on 16.10.2016.
 */
public class RectPainter extends JComponent {
//
//    private int count;
//    ArrayList<Integer> randomList = getRandomList(count);
//    {ParentSorter.k = count - 1;}
//    RectPainter(int count) { this.count = count;}
//    private ArrayList<Integer> getRandomList(int count) {
////        this.count = count;
//        ArrayList<Integer> randomList = new ArrayList<>(count);
//        for (int i = 0; i < count; i++) {
//            randomList.add(i);
//        }
//        Collections.shuffle(randomList);
//        return randomList;
//    }
//
//        @Override
//        protected void paintComponent (Graphics g){
//            super.paintComponent(g);
//            Graphics2D g2d = (Graphics2D) g;
//
//            g2d.setBackground(Color.PINK);
//            frameWidth = this.getWidth();
//            frameHeight = this.getHeight();
//
//            g2d.clearRect(0, 0, frameWidth, frameHeight);
//
//
//            int rHeight, rWidth, coefficient;
//            rWidth = (int) ((frameWidth - 1) / count);
//            coefficient = (int) ((yShift) / count);
//
////            sortingList = new RandomGenerator().getRandomList(count);
//            sortingList = bubbleSort.sort(randomList);
//            for (int i = 0; i < count; i++) {
//                rHeight = -((sortingList.get(i) * coefficient));
//                rX = i * rWidth;
//                g2d.setColor(Color.BLUE);
//                g2d.drawRect(rX, yShift, rWidth, rHeight - 4);
//                g2d.setColor(Color.YELLOW);
//                g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
//
//                if (i == bubbleSort.j) {
//                    g2d.setColor(Color.RED);
//                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
//                }
//                if (i == bubbleSort.k) {
//                    g2d.setColor(Color.GREEN);
//                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
//                }
//                if (bubbleSort.k == 0) {
//                    for (int n = 0; n < count; n++) {
//                        rHeight = -((sortingList.get(n) * coefficient));
//                        rX = n * rWidth;
//                        g2d.setColor(Color.GREEN);
//                        g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
//                        g2d.setColor(Color.black);
//                        int fontSize = (int) (rWidth / 1.5);
//                        Font f = new Font("Dialog", Font.BOLD, fontSize);
//                        g2d.setFont(f);
//                        FontMetrics fontMetrics = g2d.getFontMetrics();
//                        int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortingList.get(n))) / 2;
//                        int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
//                        g2d.drawString(String.valueOf(sortingList.get(n)), stringXCoordinate, stringYCoordinate);
//                    }
//                }
//
//                g2d.setColor(Color.black);
//                int fontSize = (int) (rWidth / 1.5);
//                Font f = new Font("Dialog", Font.BOLD, fontSize);
//                g2d.setFont(f);
//                FontMetrics fontMetrics = g2d.getFontMetrics();
//                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortingList.get(i))) / 2;
//                int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
//                g2d.drawString(String.valueOf(sortingList.get(i)), stringXCoordinate, stringYCoordinate);
//            }
//        }
}

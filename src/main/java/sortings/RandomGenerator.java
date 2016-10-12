package sortings;

import frame.BasicFrame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by WORK on 12.10.2016.
 */
public class RandomGenerator extends JComponent {
    private int count, yShift, frameWidth, frameHeight, rX;
    //    private ArrayList<Integer> randomList;
    public RandomGenerator(int yShift, int frameWidth, int frameHeight, int rX) {
        count = BasicFrame.getCount();
        this.yShift = yShift;
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.rX = rX;
//        this.randomList = randomList;
    }

    private ArrayList<Integer> getRandomList(int count) {
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            randomList.add(i);
        }
        Collections.shuffle(randomList);
        return randomList;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setBackground(Color.RED);
//            Rectangle r = getBounds();
//            g2d.clearRect(0, 0, r.width, r.height);
            g2d.clearRect(0, 0, frameWidth, frameHeight);


            int rHeight, rWidth, coefficient;
            rWidth = (int) ((frameWidth - 1) / count);
            coefficient = (int) ((yShift) / count);
//            System.out.println(coefficient);
//            ArrayList<Integer> randomList = BubbleSort.sort(getRandomList(count));
            ArrayList<Integer> randomList = getRandomList(count);
            for (int i = 0; i < count; i++) {
                rHeight = -(randomList.get(i) * coefficient);
                rX = i * rWidth;
                g2d.setColor(Color.BLUE);
                g2d.drawRect(rX, yShift, rWidth, rHeight - 4);
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                g2d.setColor(Color.black);

                int fontSize = (int)(rWidth / 1.5);
                Font f = new Font("Dialog", Font.BOLD, fontSize);
                g2d.setFont(f);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(randomList.get(i))) / 2;
                int stringYCoordinate = yShift + (int)(fontMetrics.getHeight() + 1.5);
                g2d.drawString(String.valueOf(randomList.get(i)), stringXCoordinate, stringYCoordinate);

        }
    }
}

package basicFrame;

import sortings.BubbleSort;
import sortings.ParentSorter;
import sortings.RandomGenerator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by WORK on 12.10.2016.
 */
public class BasicFrameWithClockG3 extends JFrame implements ActionListener {

    //    private Timer upperTimer;
    private GridBagConstraints gridBag = new GridBagConstraints();
    private JButton start;
    private JButton reset;
    private JSlider rectNumberSlider;
    private JSlider delaySlider;
    private UpperRandRect upperVisualPane;
    private LowerRandRect lowerVisualPane;
    private int frameWidth;
    private int frameHeight;
    private int yShift;
    //    private ArrayList<Integer> sortingList;
    private ArrayList<Integer> randomList;
    private ArrayList<Integer> randList;
    private int rX;
    private int count;
    private int delay;
    private BubbleSort bubbleSort;
    private RandomGenerator randomGenerator;
    private int upperIterationCounter;
    private int lowerIterationCounter;
    private int iterationCounter;


    // конструктор класса
    public BasicFrameWithClockG3() {

        frameWidth = 1300;
        frameHeight = 750;
        yShift = 270;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);
        setSize(frameWidth, frameHeight);

        start = new JButton("Start");
        start.addActionListener(this);
        reset = new JButton("Reset");
        reset.addActionListener(this);

        addJSliders();

        bubbleSort = new BubbleSort();
        randomGenerator = new RandomGenerator();
        count = getCount();
        randomList = getRandomList(count);
        randList = getRandList();
//        count = rectNumberSlider.getValue();

        Box buttons = Box.createHorizontalBox();
        buttons.setBorder(new EtchedBorder(Color.BLUE, Color.BLUE));
        gridBag.fill = GridBagConstraints.HORIZONTAL;
//            gridBag.ipady = 50;
        gridBag.gridx = 0;
        gridBag.gridy = 5;
        gridBag.weightx = 1;
        gridBag.weighty = 1;

        buttons.add(start);
        buttons.add(reset);
        buttons.add(rectNumberSlider);
        buttons.add(delaySlider);

        add(buttons, gridBag);
        addComponentsToPane();
    }

    /**
     * Метод для создания динамических (обновляющихся) элементов окна. В принципе, можно было оставить его
     * в конструкторе, но конструктор оказывался слишком массивным.
     */
    private void addComponentsToPane() {

        upperVisualPane = new UpperRandRect(bubbleSort, randList);
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.ipady = yShift;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        getContentPane().add(upperVisualPane, gridBag);

        lowerVisualPane = new LowerRandRect(randomGenerator);
//        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.ipady = yShift;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        getContentPane().add(lowerVisualPane, gridBag);
    }

    /**
     * Метод для создания ползунков, вынесен из конструктора, чтобы его не перегружать.
     */
    private void addJSliders() {
        rectNumberSlider = new JSlider(JSlider.HORIZONTAL, 10, yShift * 2, 70);
        rectNumberSlider.setMajorTickSpacing(20);
        rectNumberSlider.setMinorTickSpacing(5);
        rectNumberSlider.setPaintTicks(true);
        rectNumberSlider.setPaintLabels(true);
        rectNumberSlider.setSnapToTicks(true);
        rectNumberSlider.setBorder(new TitledBorder("number of items to be sorted (press \"Reset\" after setting)"));

        delaySlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        delaySlider.setMajorTickSpacing(5);
        delaySlider.setMinorTickSpacing(1);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setSnapToTicks(false);
        delaySlider.setBorder(new TitledBorder("delay"));
        delaySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                upperTimer.setDelay(delaySlider.getValue());
                lowerTimer.setDelay(delaySlider.getValue());
            }
        });
    }

    private ArrayList<Integer> getRandomList(int count) {
//        this.count = count;
        ArrayList<Integer> randomList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            randomList.add(i);
        }
        Collections.shuffle(randomList);
        return randomList;
    }

    private int getCount() {
        return rectNumberSlider.getValue();
    }

    private int getDelay() {
        return delaySlider.getValue();
    }

    private ArrayList<Integer> getRandList() {
        ArrayList<Integer> randList = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            randList.add(randomList.get(i));
        }
        return randList;
    }

//    private Graphics createVisualization(ArrayList<Integer> list) {
//
//    }


    /**
     * вложенный класс для отрисовки списка верхней панели в виде ряда прямоугольников
     */
    private class UpperRandRect extends JComponent {
        ParentSorter sorter;

        ArrayList<Integer> sortableList = new ArrayList<>();

        UpperRandRect(ParentSorter sorter, ArrayList<Integer> sortableList) {
            this.sorter = sorter;
            this.sortableList = sortableList;
            sorter.k = count - 1;
        }
//        private ParentSorter getSorter() {
//            return sorter;
//        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setBackground(Color.PINK);
            frameWidth = this.getWidth();
            frameHeight = this.getHeight();
            g2d.clearRect(0, 0, frameWidth, frameHeight);

            int rHeight, rWidth;
            int coefficient;
            rWidth = (int) ((frameWidth - 1) / count);
            coefficient = (int) (2 * (yShift) / count);

            /** sortingList - это список, который меняется после каждой сортировки */
//            ArrayList<Integer> sortingList = sorter.qSort(randList);
            sortableList = randList;
            for (int i = 0; i < count; i++) {
                rHeight = -(int) ((sortableList.get(i) * coefficient) / 2);
                rX = i * rWidth;
                g2d.setColor(Color.BLUE);
                g2d.drawRect(rX, yShift, rWidth, rHeight - 4);
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);

                if (i == sorter.j) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                }
                if (i == sorter.k) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                }
                if (sorter.k == 0) {
                    for (int n = 0; n < count; n++) {
                        rHeight = -(int) ((sortableList.get(n) * coefficient) / 2);
                        rX = n * rWidth;
                        g2d.setColor(Color.GREEN);
                        g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                        g2d.setColor(Color.black);
                        int fontSize = (int) (rWidth / 1.5);
                        Font f = new Font("Dialog", Font.BOLD, fontSize);
                        g2d.setFont(f);
                        FontMetrics fontMetrics = g2d.getFontMetrics();
                        int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortableList.get(n))) / 2;
                        int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                        g2d.drawString(String.valueOf(sortableList.get(n)), stringXCoordinate, stringYCoordinate);
                    }
                    upperTimer.stop();
                }

                g2d.setColor(Color.black);
                int fontSize = (int) (rWidth / 1.5);
                Font f = new Font("Dialog", Font.BOLD, fontSize);
                g2d.setFont(f);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortableList.get(i))) / 2;
                int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                g2d.drawString(String.valueOf(sortableList.get(i)), stringXCoordinate, stringYCoordinate);
            }

            int dataFontSize = (int) (frameWidth / 80);
            Font dataFont = new Font("TimesNewRoman", Font.BOLD, dataFontSize);
            g2d.setFont(dataFont);
            g2d.setColor(Color.BLUE);
            g2d.drawString("delay " + String.valueOf(delaySlider.getValue()) + "μs", frameWidth / 80, frameHeight / 10);
            g2d.drawString("series " + String.valueOf(count) + "items", frameWidth / 80,
                    frameHeight / 10 + frameWidth / 80 + 5);
            g2d.setColor(Color.RED);
            if (sorter.k == 0)
                iterationCounter--;
            g2d.drawString("iteration number " + String.valueOf(iterationCounter / 2), frameWidth / 80,
                    frameHeight / 10 + 2 * frameWidth / 80 + 5);
            iterationCounter++;

        }
    }

    /**
     * вложенный класс для отрисовки списка нижней панели в виде ряда прямоугольников
     */
    private class LowerRandRect extends JComponent {
        ParentSorter sorter;

        LowerRandRect(ParentSorter sorter) {
            this.sorter = sorter;
            sorter.k = count - 1;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setBackground(Color.PINK);
            frameWidth = this.getWidth();
            frameHeight = this.getHeight();
            g2d.clearRect(0, 0, frameWidth, frameHeight);

            int rHeight, rWidth;
            double coefficient;
            rWidth = (int) ((frameWidth - 1) / count);
            coefficient = (int) (2 * (yShift) / count);

//            /** sortingList - это список, который меняется после каждой сортировки */
//            ArrayList<Integer> sortingList = sorter.qSort(randomList);
            for (int i = 0; i < count; i++) {
                rHeight = -(int) ((randomList.get(i) * coefficient) / 2);
                rX = i * rWidth;
                g2d.setColor(Color.BLUE);
                g2d.drawRect(rX, yShift, rWidth, rHeight - 4);
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);

                if (i == sorter.j) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                }
                if (i == sorter.k) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                }
                if (sorter.k == 0) {
                    for (int n = 0; n < count; n++) {
                        rHeight = -(int) ((randomList.get(n) * coefficient) / 2);
                        rX = n * rWidth;
                        g2d.setColor(Color.GREEN);
                        g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                        g2d.setColor(Color.black);
                        int fontSize = (int) (rWidth / 1.5);
                        Font f = new Font("Dialog", Font.BOLD, fontSize);
                        g2d.setFont(f);
                        FontMetrics fontMetrics = g2d.getFontMetrics();
                        int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth
                                (String.valueOf(randomList.get(n))) / 2;
                        int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                        g2d.drawString(String.valueOf(randomList.get(n)), stringXCoordinate, stringYCoordinate);
                    }
                    lowerTimer.stop();
                }

                g2d.setColor(Color.black);
                int fontSize = (int) (rWidth / 1.5);
                Font f = new Font("Dialog", Font.BOLD, fontSize);
                g2d.setFont(f);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(randomList.get(i))) / 2;
                int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                g2d.drawString(String.valueOf(randomList.get(i)), stringXCoordinate, stringYCoordinate);
            }

            int dataFontSize = (int) (frameWidth / 80);
            Font dataFont = new Font("TimesNewRoman", Font.BOLD, dataFontSize);
            g2d.setFont(dataFont);
            g2d.setColor(Color.BLUE);
            g2d.drawString("delay " + String.valueOf(delaySlider.getValue()) + "μs", frameWidth / 80, frameHeight / 10);
            g2d.drawString("series " + String.valueOf(count) + "items", frameWidth / 80,
                    frameHeight / 10 + frameWidth / 80 + 5);
            g2d.setColor(Color.RED);
            if (sorter.k == 0)
                iterationCounter--;
            g2d.drawString("iteration number " + String.valueOf(iterationCounter / 2), frameWidth / 80,
                    frameHeight / 10 + 2 * frameWidth / 80 + 5);
            iterationCounter++;

        }
    }

    {
        upperIterationCounter = 0;
    }

    private Timer upperTimer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (upperIterationCounter == 0) {
                upperVisualPane.repaint();
                upperIterationCounter++;
            } else if (getDelay() < 20 && (int) (upperIterationCounter % (20.0 / (getDelay() + 1))) == 0) {
                // (тактовая частота около 50Гц)
                upperVisualPane.repaint();
                upperVisualPane.sorter.sort(randList);
                upperIterationCounter++;
            } else if (getDelay() < 20 && (int) (upperIterationCounter % (20.0 / (getDelay() + 1))) != 0) {
                upperVisualPane.sorter.sort(randList);
                upperIterationCounter++;
            } else if (getDelay() >= 20) {
                upperVisualPane.sorter.sort(randList);
                upperVisualPane.repaint();
                upperIterationCounter++;
            }
//            upperVisualPane.revalidate();
//            upperVisualPane.repaint();
        }
    });

    {
        lowerIterationCounter = 0;
    }

    private Timer lowerTimer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (lowerIterationCounter == 0) {
                lowerVisualPane.repaint();
                lowerIterationCounter++;
            } else if (delaySlider.getValue() < 20 && (int) (lowerIterationCounter % (20.0 / (delaySlider.getValue() + 1))) == 0) { // частота около 33Гц
                lowerVisualPane.repaint();
                lowerVisualPane.sorter.sort(randomList);
                lowerIterationCounter++;
            } else if (delaySlider.getValue() < 20 && (int) (lowerIterationCounter % (20.0 / (delaySlider.getValue() + 1))) != 0) {
                lowerVisualPane.sorter.sort(randomList);
                lowerIterationCounter++;
            } else if (delaySlider.getValue() >= 20) {
                lowerVisualPane.sorter.sort(randomList);
                lowerVisualPane.repaint();
                lowerIterationCounter++;
            }
        }
    });

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            upperTimer.start();
            lowerTimer.start();
            start.setText("Pause");
        } else if (e.getActionCommand().equals("Pause")) {
            upperTimer.stop();
//            upperVisualPane.repaint();
            lowerTimer.stop();
            start.setText("Start");
        } else if (e.getActionCommand().equals("Reset")) {
            upperTimer.stop();
            lowerTimer.stop();
            rX = 0;
            count = rectNumberSlider.getValue();
            randomList = getRandomList(count);
            randList = getRandList();

//            upperVisualPane = new UpperRandRect(bubbleSort, randList);
//            lowerVisualPane = new LowerRandRect(randomGenerator);
            addComponentsToPane();
            iterationCounter = 0;
            upperIterationCounter = 0;
            lowerIterationCounter = 0;
            upperVisualPane.sorter.j = 0;
            lowerVisualPane.sorter.j = 0;
//            bubbleSort.transit = 0;
//            randomGenerator.transit = 0;
            upperVisualPane.sorter.k = count - 1;
            lowerVisualPane.sorter.k = count - 1;
            start.setText("Start");
            upperVisualPane.repaint();
            lowerVisualPane.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicFrameWithClockG3());
//        getRandomList(20);
    }
}

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
public class BasicFrame extends JFrame implements ActionListener {

    //    private Timer timer;
    private GridBagConstraints gridBag = new GridBagConstraints();
    private JButton start;
    private JButton reset;
    private JSlider rectNumberSlider;
    private JSlider delaySlider;
    private RandRect topVisualPane;
    private RandRect bottomVisualPane;
    private int frameWidth;
    private int frameHeight;
    private int yShift;
    private ArrayList<Integer> sortingList;
    private ArrayList<Integer> randomList;
    private int rX;
    private int count;
    private int delay;
    private BubbleSort bubbleSort;
    private RandomGenerator randomGenerator;
    private int iterationCounter;

    /** Метод для создания динамических (обновляющихся) элементов окна. Не может быть вынесен в конструктор,
     * так как в конструкторе элементы создаются один раз при создании образца класса и не могут обновляться.
     * Вся статика (главная панель, кнопки и шкалы), напротив, вынесена в конструктор*/
    public void addComponentsToPane() {

        topVisualPane = new RandRect(bubbleSort);
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.ipady = yShift;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        getContentPane().add(topVisualPane, gridBag);

        bottomVisualPane = new RandRect(randomGenerator);
//        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.ipady = yShift;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        getContentPane().add(bottomVisualPane, gridBag);
    }

    public BasicFrame() {

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

        rectNumberSlider = new JSlider(JSlider.HORIZONTAL, 10, 300, 70);
        rectNumberSlider.setMajorTickSpacing(20);
        rectNumberSlider.setMinorTickSpacing(5);
        rectNumberSlider.setPaintTicks(true);
        rectNumberSlider.setPaintLabels(true);
        rectNumberSlider.setSnapToTicks(true);
        rectNumberSlider.setBorder(new TitledBorder("number of items to be sorted (press \"Reset\" after setting)"));

        delaySlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 10);
        delaySlider.setMajorTickSpacing(5);
        delaySlider.setMinorTickSpacing(1);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setSnapToTicks(false);
        delaySlider.setBorder(new TitledBorder("delay"));
        delaySlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timer.setDelay(delaySlider.getValue());
            }
        });

        delay = delaySlider.getValue();
        count = rectNumberSlider.getValue();

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

    /** фабричный метод для использования разных сортировок */
private ParentSorter sortFactory(ParentSorter sorter) {
//    if (sorter instanceof BubbleSort)
//        sorter = (BubbleSort)sorter;
    return sorter;
}

    // инициализация рандомизированного списка:
//    {
//        randomList = getRandomList(count);
//    }
    // инициализация образцов сортировок:
    {
        bubbleSort = new BubbleSort(count);
        randomGenerator = new RandomGenerator();
//        iterationCounter += ParentSorter.j;
    }

// вложенный класс для отрисовки списка в виде ряда прямоугольников
    private class RandRect extends JComponent {

    ParentSorter sorter;
    RandRect(ParentSorter sorter) {this.sorter = sorter;}
        {
            randomList = getRandomList(count);
//            bubbleSort.k = count - 1;
            ParentSorter.k = count - 1;
//            sorter.k = count - 1;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setBackground(Color.PINK);
            frameWidth = this.getWidth();
            frameHeight = this.getHeight();

            g2d.clearRect(0, 0, frameWidth, frameHeight);

//            int dataFontSize = (int) (frameWidth / 60);
//            Font dataFont = new Font("TimesNewRoman", Font.BOLD, dataFontSize);
//            g2d.setFont(dataFont);
//            g2d.drawString("delay " + String.valueOf(delaySlider.getValue()) + "μs", frameWidth / 20, frameHeight / 10);



            int rHeight, rWidth, coefficient;
            rWidth = (int) ((frameWidth - 1) / count);
            coefficient = (int) ((yShift) / count);

//            sortingList = new RandomGenerator().getRandomList(count);
            sortingList = sorter.sort(randomList);
            for (int i = 0; i < count; i++) {
                rHeight = -((sortingList.get(i) * coefficient));
                rX = i * rWidth;
                g2d.setColor(Color.BLUE);
                g2d.drawRect(rX, yShift, rWidth, rHeight - 4);
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);

                if (i == ParentSorter.j) {
                    g2d.setColor(Color.RED);
                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                }
                if (i == ParentSorter.k) {
                    g2d.setColor(Color.GREEN);
                    g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                }
                if (ParentSorter.k == 0) {
                    for (int n = 0; n < count; n++) {
                        rHeight = -((sortingList.get(n) * coefficient));
                        rX = n * rWidth;
                        g2d.setColor(Color.GREEN);
                        g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                        g2d.setColor(Color.black);
                        int fontSize = (int) (rWidth / 1.5);
                        Font f = new Font("Dialog", Font.BOLD, fontSize);
                        g2d.setFont(f);
                        FontMetrics fontMetrics = g2d.getFontMetrics();
                        int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortingList.get(n))) / 2;
                        int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                        g2d.drawString(String.valueOf(sortingList.get(n)), stringXCoordinate, stringYCoordinate);
                    }
                    timer.stop();
                }

                g2d.setColor(Color.black);
                int fontSize = (int) (rWidth / 1.5);
                Font f = new Font("Dialog", Font.BOLD, fontSize);
                g2d.setFont(f);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortingList.get(i))) / 2;
                int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                g2d.drawString(String.valueOf(sortingList.get(i)), stringXCoordinate, stringYCoordinate);

//                int dataFontSize = (int) (frameWidth / 60);
//                Font dataFont = new Font("TimesNewRoman", Font.BOLD, dataFontSize);
//                g2d.setFont(dataFont);
//                g2d.setColor(Color.RED);
//                g2d.drawString("iteration number " + String.valueOf(i), frameWidth / 80,
//                        frameHeight / 10 + 2 * frameWidth / 60 + 5);

            }
            int dataFontSize = (int) (frameWidth / 80);
            Font dataFont = new Font("TimesNewRoman", Font.BOLD, dataFontSize);
            g2d.setFont(dataFont);
            g2d.setColor(Color.BLUE);
            g2d.drawString("delay " + String.valueOf(delaySlider.getValue()) + "μs", frameWidth / 80, frameHeight / 10);
            g2d.drawString("series " + String.valueOf(count) + "items", frameWidth / 80,
                    frameHeight / 10 + frameWidth / 80 + 5);
            g2d.setColor(Color.RED);
            if (ParentSorter.k == 0)
                iterationCounter--;
            g2d.drawString("iteration number " + String.valueOf(iterationCounter  / 2), frameWidth / 80,
                    frameHeight / 10 + 2 * frameWidth / 80 + 5);
            iterationCounter++;

        }
    }

    private Timer timer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            count = rectNumberSlider.getValue();
            topVisualPane.repaint();
            bottomVisualPane.repaint();
        }
    });

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            timer.start();
            start.setText("Pause");
        } else if (e.getActionCommand().equals("Pause")) {
            timer.stop();
            start.setText("Start");
        } else if (e.getActionCommand().equals("Reset")) {
            timer.stop();
            rX = 0;
            count = rectNumberSlider.getValue();
            randomList = getRandomList(count);
            iterationCounter = 0;
            ParentSorter.j = 0;
            bubbleSort.transit = 0;
            ParentSorter.k = count - 1;
            start.setText("Start");
            topVisualPane.repaint();
            bottomVisualPane.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicFrame().addComponentsToPane());
//        getRandomList(20);
    }
}

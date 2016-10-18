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

    //    private Timer topTimer;
    private GridBagConstraints gridBag = new GridBagConstraints();
    private JButton start;
    private JButton reset;
    private JSlider rectNumberSlider;
    private JSlider delaySlider;
    private RandRect upperVisualPane;
    private RandRect lowerVisualPane;
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

        upperVisualPane = new RandRect(bubbleSort);
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.ipady = yShift;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        getContentPane().add(upperVisualPane, gridBag);

        lowerVisualPane = new RandRect(randomGenerator);
//        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.ipady = yShift;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        getContentPane().add(lowerVisualPane, gridBag);
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
                topTimer.setDelay(delaySlider.getValue());
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
//private ParentSorter sortFactory(ParentSorter upperSorter) {
////    if (upperSorter instanceof BubbleSort)
////        upperSorter = (BubbleSort)upperSorter;
//    return upperSorter;
//}

    // инициализация рандомизированного списка:
//    {
//        randomList = getRandomList(count);
//    }
    // инициализация образцов сортировок:
    {
        bubbleSort = new BubbleSort();
        randomGenerator = new RandomGenerator();
    }

/** вложенный класс для отрисовки списка в виде ряда прямоугольников */
    private class RandRect extends JComponent {

    // проблема здесь: один и тот же сортер this.upperSorter с помощью своего метода sort() создает один и тот же sortingList,
    // так что он скачет то туда, то туда. Нужно или 2 сортера, создающих 2 разных sortingList, или хотя бы 2 sortingList.
    // Или вообще два класса для создания графики.
    ParentSorter upperSorter;
    ParentSorter lowerSorter;
    RandRect(ParentSorter sorter) {this.upperSorter = sorter;}
        {
            randomList = getRandomList(count);
//            bubbleSort.k = count - 1;
            ParentSorter.k = count - 1;
//            upperSorter.k = count - 1;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            g2d.setBackground(Color.PINK);
            frameWidth = this.getWidth();
            frameHeight = this.getHeight();

            g2d.clearRect(0, 0, frameWidth, frameHeight);

            int rHeight, rWidth, coefficient;
            rWidth = (int) ((frameWidth - 1) / count);
            coefficient = (int) ((yShift) / count);

//            sortingList = new RandomGenerator().getRandomList(count);
            sortingList = upperSorter.sort(randomList);
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
                    topTimer.stop();
                }

                g2d.setColor(Color.black);
                int fontSize = (int) (rWidth / 1.5);
                Font f = new Font("Dialog", Font.BOLD, fontSize);
                g2d.setFont(f);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(sortingList.get(i))) / 2;
                int stringYCoordinate = yShift - (int) (fontMetrics.getHeight() + 10);
                g2d.drawString(String.valueOf(sortingList.get(i)), stringXCoordinate, stringYCoordinate);
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

    private Timer topTimer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            count = rectNumberSlider.getValue();
            upperVisualPane.repaint();
//            lowerVisualPane.repaint();
        }
    });
    private Timer bottomTimer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            count = rectNumberSlider.getValue();
//            upperVisualPane.repaint();
            lowerVisualPane.repaint();
        }
    });

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) {
            topTimer.start();
            bottomTimer.start();
            start.setText("Pause");
        } else if (e.getActionCommand().equals("Pause")) {
            topTimer.stop();
            bottomTimer.stop();
            start.setText("Start");
        } else if (e.getActionCommand().equals("Reset")) {
            topTimer.stop();
            bottomTimer.stop();
            rX = 0;
            count = rectNumberSlider.getValue();
            randomList = getRandomList(count);
            iterationCounter = 0;
            ParentSorter.j = 0;
            bubbleSort.transit = 0;
            ParentSorter.k = count - 1;
            start.setText("Start");
            upperVisualPane.repaint();
            lowerVisualPane.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicFrame().addComponentsToPane());
//        getRandomList(20);
    }
}

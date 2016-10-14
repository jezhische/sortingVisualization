package basicFrame;

import sortings.BubbleSort;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    private RandRect randRects;
    private int frameWidth;
    private int frameHeight;
    private int yShift;
    private ArrayList<Integer> randomList;
    private int rX;
    private int count;
    private int delay;

    public void addComponentsToPane() {

//        getContentPane().setLayout(new GridBagLayout());
//        gridBag = new GridBagConstraints();

        randRects = new RandRect();
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.ipady = 300;
        gridBag.weightx = 1;
        gridBag.weighty = 1;

        getContentPane().add(randRects, gridBag);

    }

    public BasicFrame() {

        getContentPane().setLayout(new GridBagLayout());

        frameWidth = 1300;
        frameHeight = 700;
        yShift = 300;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        delaySlider = new JSlider(JSlider.HORIZONTAL, 1, 500, 50);
        delaySlider.setMajorTickSpacing(20);
        delaySlider.setMinorTickSpacing(5);
        delaySlider.setPaintTicks(true);
        delaySlider.setPaintLabels(true);
        delaySlider.setSnapToTicks(true);
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
        gridBag.gridy = 4;
        gridBag.weightx = 1;
        gridBag.weighty = 1;

        buttons.add(start);
        buttons.add(reset);
        buttons.add(rectNumberSlider);
        buttons.add(delaySlider);

        add(buttons, gridBag);
    }
//
//    private ArrayList<Integer> getRandomList(int count) {
//        randomList = new ArrayList<>(count);
//        for (int i = 0; i < count; i++) {
//            randomList.add(i);
//        }
//        Collections.shuffle(randomList);
//        return randomList;
//    }

    BubbleSort bubbleSort = new BubbleSort(count);

    class RandRect extends JComponent {

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

//            randomList = new RandomGenerator().getRandomList(count);
            randomList = bubbleSort.sort();
            for (int i = 0; i < count - 1; i++) {
                rHeight = -((randomList.get(i) * coefficient) / 5);
                rX = i * rWidth;
                g2d.setColor(Color.BLUE);
                g2d.drawRect(rX, yShift, rWidth, rHeight - 4);
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(rX + 1, yShift - 1, rWidth - 2, rHeight - 1);
                g2d.setColor(Color.black);

                int fontSize = (int) (rWidth / 1.5);
                Font f = new Font("Dialog", Font.BOLD, fontSize);
                g2d.setFont(f);
                FontMetrics fontMetrics = g2d.getFontMetrics();
                int stringXCoordinate = rX + rWidth / 2 - fontMetrics.stringWidth(String.valueOf(randomList.get(i))) / 2;
                int stringYCoordinate = yShift + (int) (fontMetrics.getHeight() + 1.5);
                g2d.drawString(String.valueOf(randomList.get(i)), stringXCoordinate, stringYCoordinate);
//                randomList = bubbleSort.sort();
            }
        }
    }

    //    rectNumberSlider.getValueIsAdjusting()
    private Timer timer = new Timer(delay, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
//            count = rectNumberSlider.getValue();
            randRects.repaint();
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
            start.setText("Start");
            randRects.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BasicFrame().addComponentsToPane());
//        getRandomList(20);
    }
}

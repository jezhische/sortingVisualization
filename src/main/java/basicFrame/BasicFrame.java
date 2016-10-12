package basicFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by WORK on 12.10.2016.
 */
public class BasicFrame extends JFrame implements ActionListener {
    private Timer timer;
    private JButton start;
    private JButton reset;
    private JSlider rectNumberSlider;
    private JSlider delaySlider;
//    private RandRect randRects;
    private int frameWidth = 1300;
    private int frameHeight = 700;
    private int yShift = 300;
    //    private ArrayList<Integer> randomList;
    private int rX;
    private int count;
    private int delay;

    BasicFrame() {}



    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

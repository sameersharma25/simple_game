package com.sharma.java_tutorials;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Board extends JPanel {
    private static final int PERIOD_INTERVAL = 300;
    public static final int SQUARE_WIDTH = 55;
    public static final int BOTTOM = 304;
    private JLabel statusbar;
    private Timer timer;
    private boolean isFallingFinished = false;
    private boolean isPaused = false;
    private int numLinesRemoved = 0;
    private ArrayList<Shape> currentShapes = new ArrayList<Shape>();
    private int curX = 50;
    private int curY = 0;



    public Board(SimpleGame parent) {
        setFocusable(true);
        statusbar = parent.getStatusbar();
        addKeyListener(new TAdapter());
    }
    public void start() {
        currentShapes.add(new Shape());

        timer = new Timer(PERIOD_INTERVAL, new GameCycle());
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    private void doDrawing(Graphics g) {
        for(Shape shape : currentShapes ) {
            shape.doDrawing(g);
        }
    }

    private class TAdapter implements KeyListener {

        @Override
        public void keyPressed(KeyEvent e) {
            Shape currShape = currentShapes.get(currentShapes.size()-1);
            int keycode = e.getKeyCode();

            switch (keycode) {

                case KeyEvent.VK_P: pause();
                case KeyEvent.VK_LEFT: currShape.tryMove(curX-1 , curY); break;
                case KeyEvent.VK_RIGHT: currShape.tryMove(curX+1, curY); break;
                case KeyEvent.VK_UP: currShape.tryMove(curX, curY-1); break;
                case KeyEvent.VK_DOWN: currShape.tryMove(currShape.getCurrentXPosition(), currShape.getCurrentYPosition()+1); break;
                case KeyEvent.VK_D: currShape.oneLineDown(); break;
                case KeyEvent.VK_X: System.exit(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

        @Override
        public void keyTyped(KeyEvent e) {

        }


    }

    private void pause() {
        isPaused = !isPaused;

        if (isPaused) {

            statusbar.setText("paused");
        } else {

            statusbar.setText(String.valueOf(numLinesRemoved));
        }

        repaint();
    }


    private class GameCycle implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }

    }
    private void doGameCycle() {
        // todo get current shape
        Shape activeShape = currentShapes.get(currentShapes.size()-1);
        activeShape.setBottom(calculateBottom(activeShape));
        boolean status = activeShape.oneLineDown();
        if(status == false) {
            Shape s2 = new Shape();
            currentShapes.add(s2);
        }

        //update();
        repaint();
    }

    private int calculateBottom(Shape activeShape) {
        int newBottom = BOTTOM;
        int iter = 0;
        if(currentShapes.size() == 1)
            return BOTTOM;

        for(Shape temp: currentShapes) {
            if(newBottom > temp.getBottom() - SQUARE_WIDTH && iter < currentShapes.size()-1){
                newBottom = temp.getBottom() - SQUARE_WIDTH;
            }
            iter++;
        }
        return newBottom;
    }

    public void update() {

    }
}

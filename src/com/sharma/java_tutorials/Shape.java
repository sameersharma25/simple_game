package com.sharma.java_tutorials;

import java.awt.*;

public class Shape {
    private static final int SQUARE_WIDTH = 55;

    private int currentXPosition;
    private int currentYPosition;
    private int bottom;
    private boolean isFallingFinished = false;
    private int redScale = 0;
    private int greenScale = 0;
    private int blueScale = 0;

    public Shape() {
        this.currentXPosition = 50;
        this.currentYPosition = 0;
        this.bottom = 305;

    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    public int getCurrentXPosition() {
        return currentXPosition;
    }

    public void setCurrentYPosition(int currentYPosition) {
        this.currentYPosition = currentYPosition;
    }
    public int getCurrentYPosition() {
        return currentYPosition;
    }

    public void setCurrentXPosition(int currentXPosition) {
        this.currentXPosition = currentXPosition;
    }

    public boolean oneLineDown () {
        boolean stat = true;
        if (!tryMove(currentXPosition, currentYPosition + 1)) {

            stat = false;
        }
        return stat;
    }
    public boolean tryMove(int posX, int posY) {
        if(posY >= bottom) {
            isFallingFinished = true;
            //todo now initialize a new square.
            return false;
        }
        currentXPosition = posX;
        currentYPosition = posY;

        return true;
    }

    public void doDrawing(Graphics g) {
        g.setColor(new Color(redScale, greenScale, blueScale));
        g.fillRect(currentXPosition, currentYPosition, SQUARE_WIDTH - 2, SQUARE_WIDTH - 2);
        //g.setColor(new Color(0, 0, 0));
        g.drawString("x:" + currentXPosition + ",y:" + currentYPosition, currentXPosition + SQUARE_WIDTH/2, currentYPosition + SQUARE_WIDTH/2);
    }
}

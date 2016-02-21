package com.NewGame;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;

// -------------------------------------------------------------------------
/**
 *  This class serves to keep track of the Platform position and size.
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class Platform extends JComponent
{
    private Color myColor;
    public int x, y, width, length;

    // ----------------------------------------------------------
    /**
     * Create a new Platform object.
     * @param xPos
     * @param yPos
     */
    public Platform(int xPos, int yPos) {
        x = xPos;
        y = yPos;
        width = 50;
        length = 15;
        myColor = Color.BLACK;
    }

    public int getX() {
       return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    public void setX(int xNew) {
       x = xNew;
    }

    public void setY(int yNew) {
        y = yNew;
    }

    // ----------------------------------------------------------
    /**
     * Draw platform to GUI Panel
     * @param myBuffer
     */
    public void draw(Graphics myBuffer) {
        myBuffer.setColor(myColor);
        myBuffer.drawRect(x, y, 50, 15);
    }
}
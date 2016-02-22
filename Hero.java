package com.NewGame;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;

// -------------------------------------------------------------------------
/**
 *  This class keeps track of the Hero position.
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class Hero extends JComponent {
    private int x, y;
    private Color myColor;
    private ImageIcon hero = new ImageIcon(getClass().getResource("Hero2.png"));

    // ----------------------------------------------------------
    /**
     * Create a new Hero object.
     * @param xPos
     * @param yPos
     */
    public Hero(int xPos, int yPos) {
        x = xPos;
        y = yPos;
    }

    public int getX() {
       return x;
    }

    public int getY() {
        return y;
    }

    // ----------------------------------------------------------
    /**
     * Set x value
     * @param xNew
     */
    public void setX(int xNew) {
        x = xNew;
    }

    // ----------------------------------------------------------
    /**
     * Set y value
     * @param yNew
     */
    public void setY(int yNew) {
        y = yNew;
    }

    // ----------------------------------------------------------
    /**
     * Move Hero 40 pixels left
     */
    public void moveLeft() {
       x -= 40;
    }

    // ----------------------------------------------------------
    /**
     * Move Hero specified pixels left
     * @param xNew
     */
    public void moveLeft(int xNew) {
        x -= xNew;
    }

    // ----------------------------------------------------------
    /**
     * Move Hero 40 pixels right
     */
    public void moveRight() {
       x += 40;
    }

    // ----------------------------------------------------------
    /**
     * Move Hero specified pixels right
     * @param yNew
     */
    public void moveRight(int yNew) {
        x += yNew;
    }

    // ----------------------------------------------------------
    /**
     * Move Hero 85 pixels up
     */
    public void moveUp() {
        y -= 85;
    }

    // ----------------------------------------------------------
    /**
     * Move Hero 85 pixels down
     */
    public void moveDown() {
        y += 40;
    }

    // ----------------------------------------------------------
    /**
     * Add gravity effects to Hero position
     * @param c
     */
    public void gravity(double c) {
        y += 2 * c;
    }

    // ----------------------------------------------------------
    /**
     * Check if Hero is on Platform
     * @param list
     * @return true or false
     */
    public boolean isOnTop(ArrayList<Platform> list) {
        for(int k = 0; k < list.size(); k++) {
            if (getX() > list.get(k).getX() - 15
                && getX() < list.get(k).getX() + 30
                && getY() < list.get(k).getY() - 20
                && getY() > list.get(k).getY() - 40) {
                return true;
            }
        }
        return false;
    }

    // ----------------------------------------------------------
    /**
     * Draw Hero to GUI Panel
     * @param myBuffer
     */
    public void draw(Graphics myBuffer) {
        myBuffer.setColor(myColor);
        myBuffer.drawImage(hero.getImage(), x, y, 40, 40, null);
    }
}
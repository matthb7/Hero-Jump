package com.NewGame;

import java.util.ArrayList;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import javax.swing.JFrame;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.awt.Graphics;

// -------------------------------------------------------------------------
/**
 *  This is Hero Jump. The game involves a Hero jumping onto falling Platforms.
 *  This class serves as the main panel for the GUI.
 *
 *  @author Matthew
 *  @version Feb 21, 2016
 */
public class Panel extends JPanel implements KeyListener {
    private Hero hero;
    private BufferedImage myImage;
    private Graphics myBuffer;
    private Timer t;
    private ArrayList<Platform> list;

    public int timerCount = 25;
    public double gravityCount = 3 ;
    public int scoreCount = 0;
    public JButton start;
    public boolean onPlat = true;
    public boolean lost = false;
    public JLabel labelLevel;
    public JLabel labelScore;
    public JLabel labelEnd;

    // ----------------------------------------------------------
    /**
     * Create a new Panel object.
     */
    public Panel() {
        setLayout(new FlowLayout());

        JPanel subpanel = new JPanel();
        subpanel.setLayout(new BorderLayout());

        myImage = new BufferedImage(400, 400, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(Color.WHITE);
        myBuffer.fillRect(0, 0, 400, 400);

        hero = new Hero(200, 100);
        add(hero);
        hero.draw(myBuffer);

        list = new ArrayList<Platform>();
        Platform t1 = new Platform(200, 200);
        Platform t2 = new Platform(100, 200);
        Platform t3 = new Platform(250, 100);
        Platform t4 = new Platform(50, 150);
        list.add(t1);
        list.add(t2);
        list.add(t3);
        list.add(t4);

        start = new JButton("START");
        start.addKeyListener(new KeyList());
        subpanel.add(start, BorderLayout.NORTH);

        labelLevel = new JLabel("LEVEL: 0");
        subpanel.add(labelLevel, BorderLayout.WEST);

        labelScore = new JLabel("SCORE: " + scoreCount);
        subpanel.add(labelScore, BorderLayout.EAST);

        labelEnd = new JLabel("");
        subpanel.add(labelEnd, BorderLayout.SOUTH);

        add(subpanel);

        t = new Timer(timerCount, new Listener());
    }

    private class Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (hero.isOnTop(list)) {
                gravityCount = 3;
                hero.setX(hero.getX() + 1);
                hero.setY(hero.getY() + 1);
                onPlat = true;
            }
            else {
                gravityCount = gravityCount + .05;
                hero.gravity(gravityCount);
                onPlat = false;
            }
            myBuffer.setColor(Color.WHITE);
            myBuffer.fillRect(0, 0, 400, 400);
            if (scoreCount % 50 == 0) {
                Platform pNew = new Platform(
                    (int)(Math.random() * 400 - 100), 0);
                list.add(pNew);
            }
            scoreCount++;
            labelScore.setText("SCORE: " + scoreCount);
            if (hero.getY() > 400
                || hero.getY() < -10
                || hero.getX() > 375
                || hero.getX() < -10) {
                lost = true;
                labelEnd.setText("YOU LOSE");
                scoreCount = 0;
                t.stop();
            }
            for(int k = 0; k < list.size(); k++) {
                list.get(k).setX(list.get(k).getX() + 1);
                list.get(k).setY(list.get(k).getY() + 1);
                list.get(k).draw(myBuffer);
            }
            hero.draw(myBuffer);
            repaint();
        }
    }

    // ----------------------------------------------------------
    /**
     * Main method sets up GUI
     * @param args
     */
    public static void main(String[] args) {
        JFrame f = new JFrame("NEW GAME");
        f.setSize(800, 800);
        f.setLocation(200, 200);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setContentPane(new Panel());
        f.setVisible(true);
        f.setFocusable(true);
    }

    private class KeyList implements KeyListener {
        public void keyPressed(KeyEvent k) {
            if (k.getKeyCode() == KeyEvent.VK_SPACE) {
                scoreCount = 0;
                hero = new Hero(200, 100);
                t.start();
            }
            if (k.getKeyCode() == KeyEvent.VK_LEFT
                && onPlat == false) {
                hero.moveLeft();
            }
            if (k.getKeyCode() == KeyEvent.VK_RIGHT
                && onPlat == false) {
                hero.moveRight();
            }
            if (k.getKeyCode() == KeyEvent.VK_UP) {
                hero.moveUp();
            }
            if (k.getKeyCode() == KeyEvent.VK_DOWN) {
                hero.moveDown();
            }
            if (k.getKeyCode() == KeyEvent.VK_LEFT
                && onPlat == true) {
                hero.moveLeft(5);
            }
            if (k.getKeyCode() == KeyEvent.VK_RIGHT
                && onPlat == true) {
                hero.moveRight(5);
            }
        }

        public void keyReleased(KeyEvent k) {
            //Implementation Not Necessary
        }

        public void keyTyped(KeyEvent k) {
            //Implementation Not Necessary
        }
    }

    public void keyPressed(KeyEvent k) {
        //Implementation Not Necessary
    }

    public void keyReleased(KeyEvent k) {
        //Implementation Not Necessary
    }

    public void keyTyped(KeyEvent k) {
        //Implementation Not Necessary
    }

    public void paintComponent(Graphics g) {
       g.setColor(Color.WHITE);
       g.fillRect(0, 0, 10000, 100000);
       g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
    }
}
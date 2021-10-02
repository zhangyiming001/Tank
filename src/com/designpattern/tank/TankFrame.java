package com.designpattern.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 9:04
 * ^
 * @Description:
 **/
public class TankFrame extends Frame {

    Tank myTank = new Tank(200, 200, Dir.DOWN);
    Bullet bullet =new Bullet(300,300,Dir.UP);
    public TankFrame() {
        setSize(800, 800);
        setResizable(false);
        setTitle("坦克大战");
        setVisible(true);

        this.addKeyListener(new MykeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
        bullet.paint(g);
    }

    class MykeyListener extends KeyAdapter {

        boolean keyLEFT = false;
        boolean keyRIGHT = false;
        boolean keyUP = false;
        boolean keyDOWN = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    keyLEFT = true;
                    break;
                case KeyEvent.VK_UP:
                    keyUP = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    keyLEFT = false;
                    break;
                case KeyEvent.VK_UP:
                    keyUP = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT = false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!keyLEFT && !keyUP && !keyRIGHT && !keyDOWN) {
                myTank.setMoving(false);
            }else{
                myTank.setMoving(true);
                if (keyLEFT) myTank.setDir(Dir.LEFT);
                if (keyUP) myTank.setDir(Dir.UP);
                if (keyRIGHT) myTank.setDir(Dir.RIGHT);
                if (keyDOWN) myTank.setDir(Dir.DOWN);
            }

        }

    }
}

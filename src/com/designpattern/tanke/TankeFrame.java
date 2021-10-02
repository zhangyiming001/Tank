package com.designpattern.tanke;

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
public class TankeFrame extends Frame {

    //坦克初始位置
    int x = 200, y = 200;
    //坦克方向
    Dir dir = Dir.UP;
    //坦克速度
    final int SPEED = 10;
    public TankeFrame() {
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
        g.fillRect(x, y, 50, 50);

        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }
    }

    class MykeyListener extends KeyAdapter {

        boolean keyLEFT = false;
        boolean keyRIGHT = false;
        boolean keyUP = false;
        boolean keyDOWN = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    keyLEFT =true;
                    break;
                case KeyEvent.VK_UP:
                    keyUP =true;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT =true;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN =true;
                    break;
                default:
                    break;
            }
            setMainTankeDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    keyLEFT =false;
                    break;
                case KeyEvent.VK_UP:
                    keyUP =false;
                    break;
                case KeyEvent.VK_RIGHT:
                    keyRIGHT =false;
                    break;
                case KeyEvent.VK_DOWN:
                    keyDOWN =false;
                    break;
                default:
                    break;
            }
        }

        private void setMainTankeDir() {
            if(keyLEFT) dir = Dir.LEFT;
            if(keyUP) dir = Dir.UP;
            if(keyRIGHT) dir = Dir.RIGHT;
            if(keyDOWN) dir = Dir.DOWN;
        }

    }
}

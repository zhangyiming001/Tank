package com.designpattern.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 9:04
 * @Description:
 **/
public class TankFrame extends Frame {

    GameModel gm = GameModel.getINSTANCE();

    static final int GAME_WIDTH = 1200, GAME_HEIGHT = 900;

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
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

    /**
     * 解决屏幕闪烁：双缓存
     * 在内存中创建一张图片，并定义一只画笔（graphics）都画完后统一交给屏幕（显存）
     * 继承Frame 后 重写update 会截获paint 方法 需要在update内调用
     */
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color color = g.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        gm.paint(g);


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
                case KeyEvent.VK_LEFT -> keyLEFT = true;
                case KeyEvent.VK_UP -> keyUP = true;
                case KeyEvent.VK_RIGHT -> keyRIGHT = true;
                case KeyEvent.VK_DOWN -> keyDOWN = true;
                case KeyEvent.VK_SPACE -> gm.getMainTank().fire();
                default -> {
                }
            }
            setMainTankDir();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT -> keyLEFT = false;
                case KeyEvent.VK_UP -> keyUP = false;
                case KeyEvent.VK_RIGHT -> keyRIGHT = false;
                case KeyEvent.VK_DOWN -> keyDOWN = false;
                default -> {
                }
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            BaseTank myBaseTank = gm.getMainTank();
            if (!keyLEFT && !keyUP && !keyRIGHT && !keyDOWN) {
                myBaseTank.setMoving(false);
            } else {
                myBaseTank.setMoving(true);
                if (keyLEFT) myBaseTank.setDir(Dir.LEFT);
                if (keyUP) myBaseTank.setDir(Dir.UP);
                if (keyRIGHT) myBaseTank.setDir(Dir.RIGHT);
                if (keyDOWN) myBaseTank.setDir(Dir.DOWN);
            }

        }

    }
}

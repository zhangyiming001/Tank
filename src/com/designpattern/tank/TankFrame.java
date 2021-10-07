package com.designpattern.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 9:04
 * ^
 * @Description:
 **/
public class TankFrame extends Frame {

    BaseTank myBaseTank = new BaseTank(200, 200, Dir.DOWN, Group.GOOD, this);
    List<BaseBullet> baseBullets = new ArrayList();
    List<BaseTank> baseTanks = new ArrayList<>();
    List<BaseExplode> baseExplodes = new ArrayList<>();
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    public TankFrame() throws Exception {
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
     * 、
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
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("子弹数量："+ baseBullets.size(),10,60);
        g.drawString("敌人数量："+ baseTanks.size(),10,80);
        g.drawString("爆炸数量："+ baseExplodes.size(),10,100);
        g.setColor(c);
        myBaseTank.paint(g);
        for (int i = 0; i < baseBullets.size(); i++) {
            baseBullets.get(i).paint(g);
        }
        for (int i = 0; i < baseTanks.size(); i++) {
            baseTanks.get(i).paint(g);
        }
        for (int i = 0; i < baseExplodes.size(); i++) {
            baseExplodes.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0; i < baseBullets.size(); i++) {
            for (int j = 0; j < baseTanks.size(); j++) {
                baseBullets.get(i).collidwith(baseTanks.get(j));
            }
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
                case KeyEvent.VK_SPACE:
                    myBaseTank.fire();
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

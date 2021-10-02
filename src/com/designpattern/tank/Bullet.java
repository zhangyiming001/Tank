package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 15:13
 * ^
 * @Description:
 **/
public class Bullet {
    //子弹的宽高
    private static final int WIDTH = 15, HEIGHT = 15;
    //子弹初始位置
    private int x = 200, y = 200;
    //子弹方向
    private Dir dir;
    //子弹速度
    private static final int SPEED = 20;
    //是否存活
    private boolean live = true;
    TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame =tankFrame;
    }

    public void paint(Graphics g) {
        if (!live){
             tankFrame.bullets.remove(this);
        }
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, WIDTH, HEIGHT);
        g.setColor(color);
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_HEIGHT || y > TankFrame.GAME_WIDTH){
            live = false;
        }


    }
}

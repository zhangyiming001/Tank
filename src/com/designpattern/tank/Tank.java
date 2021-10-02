package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 13:09
 * ^
 * @Description:
 **/
public class Tank {
    //坦克初始位置
    private int x = 200, y = 200;
    //坦克方向
    private Dir dir;
    //坦克速度
    private static final int SPEED = 10;
    //目的是获取tankFrame 画笔 需要谁new的就把对象给我传进来
    private TankFrame tankFrame;
    private boolean moving = false;

    public Tank(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        Color color =g.getColor();
        g.setColor(Color.CYAN);
        g.fillRect(x, y, 50, 50);
        g.setColor(color);
        move();
    }

    private void move() {
        if (!moving){
            return;
        }
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


    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire() {
       tankFrame.bullets.add(new Bullet(this.x,this.y,this.dir,this.tankFrame));
    }
}

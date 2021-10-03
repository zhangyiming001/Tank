package com.designpattern.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 13:09
 * ^
 * @Description:
 **/
public class Tank {
    //坦克速度
    private static final int SPEED = 10;
    //坦克初始位置
    private int x, y;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    //生成随机数--坦克方向
    private Random random = new Random();
    //坦克方向
    private Dir dir;
    //目的是获取tankFrame 画笔 需要谁new的就把对象给我传进来
    private TankFrame tankFrame;
    //坦克移动
    private boolean moving = true;
    //坦克的存活状态--为碰撞检测做准备
    private boolean living = true;
    //分组
    private Group group;

    public Tank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
        if (!living) tankFrame.tanks.remove(this);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        if (!moving) {
            return;
        }
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
        if (this.group == Group.BAD && random.nextInt(100) > 85) {
            this.fire();
            randomDir();
        }
        //边界检测
        boundsCheck();

    }

    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void fire() {
        int bulletX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bulletY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;

        tankFrame.bullets.add(new Bullet(bulletX, bulletY, this.dir, this.group, this.tankFrame));
    }

    public void die() {
        this.living = false;
    }
}

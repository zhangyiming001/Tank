package com.designpattern.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 13:09
 * ^
 * @Description:
 **/
public class BaseTank {
    //坦克速度
    private static final int SPEED = 10;
    //坦克初始位置
    public int x, y;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    //实现矩形的类
    Rectangle rectangle = new Rectangle();
    //生成随机数--坦克方向
    private Random random = new Random();
    //坦克方向
    Dir dir;
//    //目的是获取tankFrame 画笔 需要谁new的就把对象给我传进来
//    TankFrame tankFrame;
    //坦克移动
    private boolean moving = true;
    //坦克的存活状态--为碰撞检测做准备
    private boolean living = true;
    //分组
    Group group;
    //策略模式 -- 设计模式
//    FireStrategy fireStrategy = new DefaultFireStrategy(); //默认策略模式
//    FireStrategy fireStrategy = new FourDirFireStrategy(); //四个方向的的策略模式
      FireStrategy fireStrategy; //动态创建
    GameModel gameModel;
    public BaseTank(int x, int y, Dir dir, Group group, GameModel gameModel) throws Exception {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;

        //每次初始化的时候都为 rectangle 赋一次值
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        if (group == Group.GOOD) {
//            fireStrategy = new FourDirFireStrategy();
            String goodFireStrategy = (String) PropertyMgr.get("goodFireStrategy");
            fireStrategy = (FireStrategy) Class.forName(goodFireStrategy).getDeclaredConstructor().newInstance();

        }else{
//            fireStrategy = new DefaultFireStrategy();
            String badFireStrategy = (String) PropertyMgr.get("badFireStrategy");
            fireStrategy = (FireStrategy) Class.forName(badFireStrategy).getDeclaredConstructor().newInstance();

        }

    }

    public void paint(Graphics g) {
        if (!living) gameModel.baseTanks.remove(this);
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
        //更新 rectangle 的值
        rectangle.x = this.x;
        rectangle.y = this.y;
    }

    private void boundsCheck() {
        if (this.x < 0) x = 0;
        if (this.y < 30) y = 30;
        if (this.x > TankFrame.GAME_WIDTH - com.designpattern.tank.BaseTank.WIDTH) x = TankFrame.GAME_WIDTH - com.designpattern.tank.BaseTank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - com.designpattern.tank.BaseTank.HEIGHT) y = TankFrame.GAME_HEIGHT - com.designpattern.tank.BaseTank.HEIGHT;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }


    public void fire() {
        fireStrategy.fire(this);
    }

    public void die() {
        this.living = false;
    }


    /***********************************Get/Set****************************************************/
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
}

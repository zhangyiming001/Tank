package com.designpattern.tank;

import com.designpattern.tank.observer.TankFireEvent;
import com.designpattern.tank.observer.TankFireHandler;
import com.designpattern.tank.observer.TankFireObserver;
import com.designpattern.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.List;
import java.util.Random;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 13:09
 * @Description: 继承 GameObject 是为了实现调停者模式
 **/
public class BaseTank extends GameObject {
    //坦克速度
    private static final int SPEED = 10;
    //坦克相撞回到上一次位置
    int oldX, oldY;
    public static final int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static final int HEIGHT = ResourceMgr.goodTankU.getHeight();
    //实现矩形的类
    public Rectangle rectangle = new Rectangle();
    //生成随机数--坦克方向
    private final Random random = new Random();
    //坦克方向
    public Dir dir;
    //    //目的是获取tankFrame 画笔 需要谁new的就把对象给我传进来
//    TankFrame tankFrame;
    //坦克移动
    private boolean moving = true;
    //坦克的存活状态--为碰撞检测做准备
    private boolean living = true;
    //分组
    public Group group;
    //策略模式 -- 设计模式
//    FireStrategy fireStrategy = new DefaultFireStrategy(); //默认策略模式
//    FireStrategy fireStrategy = new FourDirFireStrategy(); //四个方向的的策略模式
    FireStrategy fireStrategy; //动态创建

    public BaseTank(int x, int y, Dir dir, Group group) {
        super.x = x;
        super.y = y;
        this.dir = dir;
        this.group = group;

        //每次初始化的时候都为 rectangle 赋一次值
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;
        try {
            if (group == Group.GOOD) {
//            fireStrategy = new FourDirFireStrategy();
                String goodFireStrategy = (String) PropertyMgr.get("goodFireStrategy");
                fireStrategy = (FireStrategy) Class.forName(goodFireStrategy).getDeclaredConstructor().newInstance();

            } else {
//            fireStrategy = new DefaultFireStrategy();
                String badFireStrategy = (String) PropertyMgr.get("badFireStrategy");
                fireStrategy = (FireStrategy) Class.forName(badFireStrategy).getDeclaredConstructor().newInstance();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        GameModel.getINSTANCE().add(this);


    }

    @Override
    public void paint(Graphics g) {
        if (!living) GameModel.getINSTANCE().remove(this);
        switch (dir) {
            case LEFT -> g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
            case UP -> g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
            case RIGHT -> g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
            case DOWN -> g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
            default -> {
            }
        }
        move();
    }
    private final List<TankFireObserver> fireObservers = List.of(new TankFireHandler());
    public void handleFireKey(){
        TankFireEvent event = new TankFireEvent(this);
        for (TankFireObserver o:fireObservers) {
            o.actionOnFire(event);
        }
    }
    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public void back() {
        x = oldX;
        y = oldY;
    }

    private void move() {
        //记录移动之前的位置
        oldX = x;
        oldY = y;
        if (!moving) {
            return;
        }
        switch (dir) {
            case LEFT -> x -= SPEED;
            case UP -> y -= SPEED;
            case RIGHT -> x += SPEED;
            case DOWN -> y += SPEED;
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
        if (this.x > TankFrame.GAME_WIDTH - com.designpattern.tank.BaseTank.WIDTH)
            x = TankFrame.GAME_WIDTH - com.designpattern.tank.BaseTank.WIDTH;
        if (this.y > TankFrame.GAME_HEIGHT - com.designpattern.tank.BaseTank.HEIGHT)
            y = TankFrame.GAME_HEIGHT - com.designpattern.tank.BaseTank.HEIGHT;
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
    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }

    public Group getGroup() {
        return group;
    }

}

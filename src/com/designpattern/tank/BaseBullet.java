package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 15:13
 * ^
 * @Description:
 **/
public class BaseBullet {
    //子弹速度
    private static final int SPEED = 20;
    //子弹的宽高
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    //实现矩形的类
    Rectangle rectangle = new Rectangle();
    private Group group;
    //子弹初始位置
    private int x, y;
    //子弹方向
    private Dir dir;
    //是否存活--为碰撞检测做准备
    private boolean living = true;

    GameModel gameModel;

    public BaseBullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
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

        gameModel.baseBullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            gameModel.baseBullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }
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
        //更新 rectangle 的值
        rectangle.x = this.x;
        rectangle.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void collidwith(BaseTank baseTank) {

        if (this.getGroup() == baseTank.getGroup()) return;

        //TODO 潜在的BUG 每次碰撞检测都要new两个对象 这就相当于2mn 的复杂度  通过new java自带的rectangle 来记录位置代替new对象
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(baseTank.getX(), baseTank.getY(), BaseTank.WIDTH, BaseTank.HEIGHT);

        if (rectangle.intersects(baseTank.rectangle)) {
            baseTank.die();
            this.die();
            int bulletX = baseTank.getX() + com.designpattern.tank.BaseTank.WIDTH/2 - BaseExplode.WIDTH/2;
            int bulletY = baseTank.getY() + com.designpattern.tank.BaseTank.HEIGHT/2 - BaseExplode.HEIGHT/2;
            gameModel.baseExplodes.add(new BaseExplode(bulletX,bulletY,gameModel));
        }
    }

    private void die() {
        this.living = false;
    }
}

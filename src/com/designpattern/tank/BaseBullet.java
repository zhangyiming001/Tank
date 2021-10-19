package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 15:13
 * ^
 * @Description:
 **/
public class BaseBullet extends GameObject{
    //子弹速度
    private static final int SPEED = 20;
    //子弹的宽高
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    //实现矩形的类
    public Rectangle rectangle = new Rectangle();
    private final Group group;
    //子弹方向
    private final Dir dir;
    //是否存活--为碰撞检测做准备
    private boolean living = true;

    public BaseBullet(int x, int y, Dir dir, Group group) {
        super.x = x;
        super.y = y;
        this.dir = dir;
        this.group = group;

        //每次初始化的时候都为 rectangle 赋一次值
        rectangle.x = this.x;
        rectangle.y = this.y;
        rectangle.width = WIDTH;
        rectangle.height = HEIGHT;

        GameModel.getINSTANCE().add(this);
    }

    public Group getGroup() {
        return group;
    }
    @Override
    public void paint(Graphics g) {
        if (!living) {
            GameModel.getINSTANCE().remove(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        switch (dir) {
            case LEFT -> x -= SPEED;
            case UP -> y -= SPEED;
            case RIGHT -> x += SPEED;
            case DOWN -> y += SPEED;
        }
        //更新 rectangle 的值
        rectangle.x = this.x;
        rectangle.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

/**    public boolean collidwith(BaseTank baseTank) {

        if (this.getGroup() == baseTank.getGroup()) return false;

        //TODO 潜在的BUG 每次碰撞检测都要new两个对象 这就相当于2mn 的复杂度  通过new java自带的rectangle 来记录位置代替new对象
//        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
//        Rectangle rect2 = new Rectangle(baseTank.getX(), baseTank.getY(), BaseTank.WIDTH, BaseTank.HEIGHT);

        if (rectangle.intersects(baseTank.rectangle)) {
            baseTank.die();
            this.die();
            int bulletX = baseTank.getX() + com.designpattern.tank.BaseTank.WIDTH/2 - BaseExplode.WIDTH/2;
            int bulletY = baseTank.getY() + com.designpattern.tank.BaseTank.HEIGHT/2 - BaseExplode.HEIGHT/2;
            gameModel.add(new BaseExplode(bulletX,bulletY,gameModel));
            return true;
        }

        return false;
    }*/

    public void die() {
        this.living = false;
    }
}

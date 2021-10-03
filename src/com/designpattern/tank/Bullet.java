package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/2 15:13
 * ^
 * @Description:
 **/
public class Bullet {
    //子弹速度
    private static final int SPEED = 20;
    //子弹的宽高
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();

    private Group group;
    //子弹初始位置
    private int x, y;
    //子弹方向
    private Dir dir;
    //是否存活--为碰撞检测做准备
    private boolean living = true;

    TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tankFrame = tankFrame;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g) {
        if (!living) {
            tankFrame.bullets.remove(this);
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) {
            living = false;
        }
    }

    public void collidwith(Tank tank) {

        if (this.getGroup() == tank.getGroup()) return;


        Rectangle rect1 = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rect1.intersects(rect2)) {
            tank.die();
            this.die();
            int bulletX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int bulletY = tank.getY() + Tank.HEIGHT/2 - Explode.HEIGHT/2;
            tankFrame.explodes.add(new Explode(bulletX,bulletY,tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }
}

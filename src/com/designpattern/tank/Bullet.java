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
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    //子弹初始位置
    private int x = 200, y = 200;
    //子弹方向
    private Dir dir;
    //子弹速度
    private static final int SPEED = 20;
    //是否存活--为碰撞检测做准备
    private boolean living = true;

    TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame =tankFrame;
    }

    public void paint(Graphics g) {
        if (!living){
             tankFrame.bullets.remove(this);
        }
        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
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
        if (x < 0 || y < 0 || x > TankFrame.GAME_HEIGHT || y > TankFrame.GAME_WIDTH){
            living = false;
        }
    }
    public void collidwith(Tank tank){
        Rectangle rect1 = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(),tank.getX(),Tank.WIDTH,Tank.HEIGHT);
        if (rect1.intersects(rect2)){
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}

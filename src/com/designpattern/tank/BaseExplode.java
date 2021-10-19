package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/3 13:30
 * @Description:
 **/
public class BaseExplode extends GameObject {
    private int stap = 0;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();//爆炸的宽
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();//爆炸的高

    public BaseExplode(int x, int y) {
        super.x = x;
        super.y = y;

        new Thread(() -> new Audio("audio//explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[stap++], x, y, null);

        if (stap >= ResourceMgr.explodes.length) {
            GameModel.getINSTANCE().remove(this);
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

}

package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/3 13:30
 * ^
 * @Description:
 **/
public class BaseExplode {
    //子弹初始位置
    private int x, y;
    TankFrame tankFrame;
    private int stap = 0;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();//爆炸的宽
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();//爆炸的高

    public BaseExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Thread(() -> new Audio("audio//explode.wav").play()).start();
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[stap++], x, y, null);

        if (stap >= ResourceMgr.explodes.length) {
            tankFrame.baseExplodes.remove(this);
        }
    }

}

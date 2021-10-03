package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/3 13:30
 * ^
 * @Description:
 **/
public class Explode {
    //子弹初始位置
    private int x, y;
    TankFrame tankFrame;
    private int stap = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Audio("audio//explode.wav").play();

    }



    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[stap++],x,y,null);
        if (stap>=ResourceMgr.explodes.length){
            stap = 0;
        }
    }

}

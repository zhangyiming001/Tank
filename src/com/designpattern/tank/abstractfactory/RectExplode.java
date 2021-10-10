package com.designpattern.tank.abstractfactory;


import com.designpattern.tank.Audio;
import com.designpattern.tank.ResourceMgr;
import com.designpattern.tank.TankFrame;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/3 13:30
 * ^
 * @Description:
 **/
public class RectExplode extends Explode {
    //子弹初始位置
    private int x, y;
    TankFrame tankFrame;
    private int step = 0;
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();//爆炸的宽
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();//爆炸的高

    public RectExplode( int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        new Thread(() -> new Audio("audio//explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if (step>=5)
            tankFrame.baseExplodes.remove(this);

        g.setColor(color);
    }

}

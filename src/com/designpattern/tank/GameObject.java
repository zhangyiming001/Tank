package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 13:03
 * ^
 * @Description:
 **/
public abstract class GameObject {
    public int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}

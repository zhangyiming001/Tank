package com.designpattern.tank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 13:03
 * @Description: 调停者/中介者模式（Mediator）
 **/
public abstract class GameObject {
    public int x,y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
}

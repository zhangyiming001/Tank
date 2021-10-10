package com.designpattern.tank.abstractfactory;

import com.designpattern.tank.BaseTank;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/7 14:18
 * ^
 * @Description:
 **/
public abstract class Bullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank tank);
}

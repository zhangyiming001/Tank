package com.designpattern.tank.abstractfactory;

import com.designpattern.tank.Group;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/7 14:18
 * ^
 * @Description:
 **/
public abstract class Tank {
    //分组
    public Group group =Group.BAD;
    //实现矩形的类
    public Rectangle rectangle = new Rectangle();
    public abstract void paint(Graphics g);

    public  Group getGroup(){
        return  this.group;
    };

    public abstract void die();

    public abstract int getX();

    public abstract int getY();
}

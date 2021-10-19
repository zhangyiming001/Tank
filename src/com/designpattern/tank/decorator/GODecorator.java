package com.designpattern.tank.decorator;

import com.designpattern.tank.GameObject;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/18 15:04
 * ^
 * @Description:
 **/
public abstract class GODecorator extends GameObject {
    GameObject gameObject;

    public GODecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void paint(Graphics g) {
        gameObject.paint(g);
    }

}

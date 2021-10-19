package com.designpattern.tank.decorator;

import com.designpattern.tank.GameObject;

import java.awt.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/18 15:06
 * ^
 * @Description:
 **/
public class RectDecorator extends GODecorator {

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        this.x = gameObject.x;
        this.y = gameObject.y;
        super.paint(g);
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        g.drawRect(gameObject.x,gameObject.y,gameObject.getWidth(),gameObject.getWidth());
        g.setColor(color);
    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObject.getWidth();
    }
}

package com.designpattern.tank.abstractfactory;

import com.designpattern.tank.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/7 15:03
 * ^
 * @Description:
 **/
public class RectFactory extends GameFactory{
    @Override
    public Tank crateTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) throws Exception {
        return new RectTank(x,y, dir, group, tankFrame);
    }

    @Override
    public Bullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new RectBullet(x, y, dir, group, tankFrame);
    }

    @Override
    public Explode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x, y, tankFrame);
    }
}

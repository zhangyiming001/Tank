package com.designpattern.tank.abstractfactory;

import com.designpattern.tank.*;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/7 15:03
 * ^
 * @Description:
 **/
public class DefaultFactory extends GameFactory{
    @Override
    public Tank crateTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) throws Exception {
        return new BaseTank(x,y, dir, group, tankFrame);

    }

    @Override
    public Bullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new BaseBullet(x, y, dir, group, tankFrame);
    }

    @Override
    public Explode createExplode(int x, int y, TankFrame tankFrame) {
        return new BaseExplode(x, y, tankFrame);
    }
}

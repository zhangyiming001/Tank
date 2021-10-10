package com.designpattern.tank.abstractfactory;

import com.designpattern.tank.Dir;
import com.designpattern.tank.Group;
import com.designpattern.tank.TankFrame;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/7 14:12
 * ^
 * @Description:
 **/
public abstract class GameFactory {
    public abstract Tank crateTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) throws Exception;
    public abstract Bullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract Explode createExplode(int x, int y, TankFrame tankFrame);
}

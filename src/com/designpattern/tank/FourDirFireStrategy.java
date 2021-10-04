package com.designpattern.tank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/4 12:16
 * ^
 * @Description:默认的策略
 **/
public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(Tank tank) {
        int bulletX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bulletY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir: dirs) {
            new Bullet(bulletX, bulletY, dir, tank.group, tank.tankFrame);

        }


    }
}

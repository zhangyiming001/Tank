package com.designpattern.tank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/4 12:16
 * ^
 * @Description:默认的策略
 **/
public class FourDirFireStrategy implements FireStrategy{
    @Override
    public void fire(BaseTank baseTank) {
        int bulletX = baseTank.x + com.designpattern.tank.BaseTank.WIDTH / 2 - BaseBullet.WIDTH / 2;
        int bulletY = baseTank.y + com.designpattern.tank.BaseTank.HEIGHT / 2 - BaseBullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();
        for (Dir dir: dirs) {
            baseTank.tankFrame.gameFactory.createBullet(bulletX, bulletY, dir, baseTank.group, baseTank.tankFrame);

        }


    }
}

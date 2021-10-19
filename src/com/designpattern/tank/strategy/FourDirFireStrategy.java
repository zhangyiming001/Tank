package com.designpattern.tank.strategy;

import com.designpattern.tank.BaseBullet;
import com.designpattern.tank.BaseTank;
import com.designpattern.tank.Dir;
import com.designpattern.tank.GameModel;
import com.designpattern.tank.decorator.RectDecorator;
import com.designpattern.tank.decorator.TailDecorator;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/4 12:16
 * @Description: 默认的策略
 **/
public class FourDirFireStrategy implements FireStrategy {
    @Override
    public void fire(BaseTank baseTank) {
        int bulletX = baseTank.x + com.designpattern.tank.BaseTank.WIDTH / 2 - BaseBullet.WIDTH / 2;
        int bulletY = baseTank.y + com.designpattern.tank.BaseTank.HEIGHT / 2 - BaseBullet.HEIGHT / 2;
        Dir[] dirs = Dir.values();

        for (Dir dir: dirs) {
            //装饰模式
//            BaseBullet baseBullet = new BaseBullet(bulletX, bulletY, dir, baseTank.group);
//            GameModel.getINSTANCE().add(new RectDecorator(new TailDecorator(baseBullet)));
            new BaseBullet(bulletX, bulletY, dir, baseTank.group);

        }


    }
}

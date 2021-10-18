package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.BaseBullet;
import com.designpattern.tank.BaseExplode;
import com.designpattern.tank.BaseTank;
import com.designpattern.tank.GameObject;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/16 14:42
 * ^
 * @Description:
 **/
public class BulletTankCollider implements Collider{
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseBullet baseBullet && o2 instanceof BaseTank baseTank){
            if (baseBullet.getGroup() == baseTank.getGroup()) return true;

            if (baseBullet.rectangle.intersects(baseTank.rectangle)) {
                baseTank.die();
                baseBullet.die();
                int bulletX = baseTank.getX() + com.designpattern.tank.BaseTank.WIDTH/2 - BaseExplode.WIDTH/2;
                int bulletY = baseTank.getY() + com.designpattern.tank.BaseTank.HEIGHT/2 - BaseExplode.HEIGHT/2;
                baseTank.gameModel.add(new BaseExplode(bulletX,bulletY,baseTank.gameModel));
                return false;
            }
        }else if (o1 instanceof BaseTank && o2 instanceof BaseBullet){
            return collide(o2,o1);
        }
        return true;
    }
}

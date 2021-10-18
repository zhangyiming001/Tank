package com.designpattern.tank.chainofresponsibility;

import com.designpattern.tank.BaseBullet;
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
        if (o1 instanceof BaseBullet && o2 instanceof BaseTank){
            BaseBullet baseBullet = (BaseBullet) o1;
            BaseTank baseTank = (BaseTank) o2;
            if(baseBullet.collidwith(baseTank)){
                return false;
            }

        }else if (o1 instanceof BaseTank && o2 instanceof BaseBullet){
            return collide(o2,o1);
        }
        return true;
    }
}

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
public class TankTankCollider implements Collider{
    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof BaseTank && o2 instanceof BaseTank){
            BaseTank baseTank1 = (BaseTank) o1;
            BaseTank baseTank2 = (BaseTank) o2;
            if (baseTank1.getRectangle().intersects(baseTank2.getRectangle())){
                baseTank1.Stop();
                baseTank2.Stop();
            }
        }else if (o1 instanceof BaseTank && o2 instanceof BaseBullet){
            collide(o2,o1);
        }
    }
}

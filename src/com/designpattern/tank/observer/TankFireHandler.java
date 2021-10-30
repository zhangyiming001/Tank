package com.designpattern.tank.observer;

import com.designpattern.tank.BaseTank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/30 12:51
 * @Description:
 **/
public class TankFireHandler implements TankFireObserver{
    @Override
    public void actionOnFire(TankFireEvent tankFireEvent) {
        BaseTank baseTank = tankFireEvent.getSource();
        baseTank.fire();
    }
}

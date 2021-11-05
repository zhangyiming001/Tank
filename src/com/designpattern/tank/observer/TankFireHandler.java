package com.designpattern.tank.observer;

import com.designpattern.tank.BaseTank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/30 12:51
 * @Description: 坦克具体的处理者，获取事件源对象操作具体的事件
 **/
public class TankFireHandler implements TankFireObserver{
    @Override
    public void actionOnFire(TankFireEvent tankFireEvent) {
        BaseTank baseTank = tankFireEvent.getSource();
        baseTank.fire();
    }
}

package com.designpattern.tank.observer;

import com.designpattern.tank.BaseTank;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/30 12:48
 * @Description:
 **/
public class TankFireEvent {
    BaseTank baseTank;
    public BaseTank getSource(){
        return baseTank;
    }
    public TankFireEvent(BaseTank baseTank) {
        this.baseTank = baseTank;
    }
}

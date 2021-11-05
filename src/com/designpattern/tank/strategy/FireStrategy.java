package com.designpattern.tank.strategy;

import com.designpattern.tank.BaseTank;

import java.io.Serializable;

/**
 * @Author: ZhangYiMing
 * @CreateDate: 2021/10/4 12:14
 * @Description: 策略模式
 **/
public interface FireStrategy extends Serializable {
    void fire(BaseTank baseTank);
}

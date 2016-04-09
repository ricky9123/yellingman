package com.hit.hackgame.handle;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 * Created by dusz2 on 2016/4/10 0010.
 */
public class YellingRobot {

    private Robot robot = null;

    public YellingRobot(){

        try{
            robot = new Robot();
        }catch (AWTException e){
            e.printStackTrace();
        }

    }

    public void keybotPress(){
        robot.keyPress(KeyEvent.VK_Z);
        robot.keyRelease(KeyEvent.VK_Z);
    }


}

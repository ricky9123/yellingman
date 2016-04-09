package com.hit.cqcoder.vocaltest2;

import android.nfc.Tag;
import android.util.Log;

/**
 * Created by Dell-pc on 2016/4/9.
 */
public class JumpLevel {

    AudioCheck ac;
    private Object lock;

    private int level;
    private String TAG = "JL";

    public void exchangeLevel() {

        lock = new Object();


        new Thread(new Runnable() {
            @Override
            public void run() {

                ac = new AudioCheck();

                while (true) {

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ac.getNoiseLevel();
                    Log.i(TAG, String.valueOf(ac.getVolume()));

                    if (ac.getVolume() < 65) {

                        level = 0;

                    } else if (ac.getVolume() >= 65 && ac.getVolume() < 75){

                        level = 1;

                    }else if(ac.getVolume() >= 75 && ac.getVolume() < 99){

                        level = 2;
                    }else{

                        level = 3;

                    }

                    synchronized (lock) {
                        try {
                            lock.wait(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }).start();

    }

    public int getLevel() {

        return level;
    }

}

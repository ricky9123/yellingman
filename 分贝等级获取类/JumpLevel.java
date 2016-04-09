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

                    if (ac.getVolume() < 45) {

                        level = 0;

                    } else {

                        level = 1;

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

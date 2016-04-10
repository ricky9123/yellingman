package com.hit.hackgame;

/**
 * Created by Administrator on 2016/4/6.
 */

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

/**
 * Created by greatpresident on 2014/8/5.
 */
public class JumpLevel {
    private static JumpLevel jump;

    private static final String TAG = "hehe";
    static final int SAMPLE_RATE_IN_HZ = 8000;
    static final int BUFFER_SIZE = AudioRecord.getMinBufferSize(SAMPLE_RATE_IN_HZ,
            AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
    AudioRecord mAudioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
            SAMPLE_RATE_IN_HZ, AudioFormat.CHANNEL_IN_DEFAULT,
            AudioFormat.ENCODING_PCM_16BIT, BUFFER_SIZE);
    boolean isGetVoiceRun;
    Object mLock;
    double volume;
    public static Thread thread;

    public static int jumpLevel = 0;

    private JumpLevel() {
        mLock = new Object();
    }

    public static JumpLevel getJumpLevel() {
        if (jump == null)
            jump = new JumpLevel();
        return jump;
    }

    public void getNoiseLevel() {
        if (isGetVoiceRun) {
            // Log.e(TAG, "����¼����");
            return;
        }

        if (mAudioRecord == null) {
            // Log.e("sound", "mAudioRecord��ʼ��ʧ��");
        }
        isGetVoiceRun = true;

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                mAudioRecord.startRecording();
                short[] buffer = new short[BUFFER_SIZE];
                while (isGetVoiceRun) {
                    int r = mAudioRecord.read(buffer, 0, BUFFER_SIZE);
                    long v = 0;

                    for (int i = 0; i < buffer.length; i++) {
                        v += buffer[i] * buffer[i];
                    }

                    double mean = v / (double) r;

                    if (mean >= 1){


                        volume = 10 * Math.log10(mean);
                    }else{

                        volume = 0;

                    }

                    Log.d(TAG, "" + volume);

                    if (volume < 80) {
                        jumpLevel = 0;

                    } else if (volume >= 80 && volume < 82) {

                        jumpLevel = 1;

                    } else if (volume >= 82) {
                        jumpLevel = 2;


                    } else {
                        ;

                    }

                    Log.d(TAG, "" + jumpLevel);

                    synchronized (mLock) {
                        try {
                            mLock.wait(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                mAudioRecord.stop();
                mAudioRecord.release();
                mAudioRecord = null;
            }
        });

        thread.start();
    }
}

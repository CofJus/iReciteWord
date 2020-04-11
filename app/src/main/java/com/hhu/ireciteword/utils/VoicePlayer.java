package com.hhu.ireciteword.utils;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by JiRui on 2020/4/7
 * 通过URL播放音频
 */

public class VoicePlayer {
    public void playVoice(String ttsURL){
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(ttsURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
    }
}

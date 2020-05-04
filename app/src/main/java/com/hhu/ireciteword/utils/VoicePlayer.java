package com.hhu.ireciteword.utils;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by JiRui on 2020/4/7
 * 通过URL播放音频
 */

public class VoicePlayer {
    public static void playVoice(String url){
        //播放音频
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
    }
}

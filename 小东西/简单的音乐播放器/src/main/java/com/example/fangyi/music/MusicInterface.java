package com.example.fangyi.music;

/**
 * Created by FANGYI on 2016/5/21.
 */
public interface MusicInterface {

    void play();
    void pause();
    void continuePlay();
    void stop();
    void seekTo(int progress);
}

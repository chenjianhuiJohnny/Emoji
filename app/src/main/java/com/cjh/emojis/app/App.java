package com.cjh.emojis.app;

import android.app.Application;

import com.cjh.emoji.utils.EmojiUtil;

/**
 * Created by chenjianhui on 2017/4/20.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        EmojiUtil.getInstance().init(this);
    }
}

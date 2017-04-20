package com.cjh.emoji;

import java.util.List;

/**
 * Created by chenjianhui on 2017/3/2.
 */

public class EmojiParentBean {
    private String name;
    private String path;
    private List<EmojiPathBean> mEmojiBeen;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<EmojiPathBean> getEmojiBeen() {
        return mEmojiBeen;
    }

    public void setEmojiBeen(List<EmojiPathBean> emojiBeen) {
        mEmojiBeen = emojiBeen;
    }
}

package com.cjh.emoji;

/**
 * Created by chenjianhui on 2017/3/1.
 */

public class EmojiPathBean {
    private String path;
    private String name;

    public EmojiPathBean(String path, String name) {
        this.path = path;
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "EmojiPathBean{" +
                "path='" + path + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}

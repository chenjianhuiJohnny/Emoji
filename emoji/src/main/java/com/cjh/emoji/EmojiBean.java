package com.cjh.emoji;

/**
 * Created by chenjianhui on 2017/3/1.
 */

public class EmojiBean {
    private int start;
    private int end;
    private String type;

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "EmojiBean{" +
                "start=" + start +
                ", end=" + end +
                ", type='" + type + '\'' +
                '}';
    }
}

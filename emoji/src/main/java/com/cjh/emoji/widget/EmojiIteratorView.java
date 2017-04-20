package com.cjh.emoji.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.cjh.emoji.utils.Utils;

/**
 * Created by chenjianhui on 2017/3/2.
 */

public class EmojiIteratorView extends View {

    private static int DP_DEFAULT_CLE_WIDTH = 3;
    private int mDefaultCleWidth = 10;
    private int mDefaultCleDistance = mDefaultCleWidth * 4;
    private Paint mPaintFocus;
    private Paint mPaintNormal;
    private int mIntCount = 6;
    private int mFocusItem = 0;

    public EmojiIteratorView(Context context) {
        this(context, null);
    }

    public EmojiIteratorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiIteratorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mDefaultCleWidth = Utils.dpToPx(DP_DEFAULT_CLE_WIDTH, getResources());
        mDefaultCleDistance = mDefaultCleWidth * 4;

        mPaintFocus = new Paint();
        mPaintFocus.setAntiAlias(true);
        mPaintFocus.setStyle(Paint.Style.FILL);
        mPaintFocus.setColor(Color.argb(255, 189, 189, 189));

        mPaintNormal = new Paint();
        mPaintNormal.setAntiAlias(true);
        mPaintNormal.setStyle(Paint.Style.FILL);
        mPaintNormal.setColor(Color.argb(255, 238, 238, 238));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;
        int height = 0;

        if (modeWidth == MeasureSpec.EXACTLY) {
            width = sizeWidth;
        } else {
            width = mDefaultCleDistance * 7;
        }

        if (modeHeight == MeasureSpec.EXACTLY) {
            height = sizeHeight;
        } else {
            height = mDefaultCleWidth * 2;
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 1; i <= mIntCount; i++) {
            canvas.drawCircle(mDefaultCleDistance * i, mDefaultCleWidth, mDefaultCleWidth, mPaintNormal);
        }

        if (mFocusItem > 0) {
            canvas.drawCircle(mDefaultCleDistance * mFocusItem, mDefaultCleWidth, mDefaultCleWidth, mPaintFocus);
        }
    }

    public void setFocusItem(int focusItem) {
        mFocusItem = focusItem;
        invalidate();
    }
}



















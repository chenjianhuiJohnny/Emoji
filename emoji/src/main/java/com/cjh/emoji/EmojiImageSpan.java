package com.cjh.emoji;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.text.style.ImageSpan;

/**
 * Created by chenjianhui on 2017/2/28.
 */

public class EmojiImageSpan extends ImageSpan {
    public EmojiImageSpan(Bitmap b) {
        super(b);
    }

    public EmojiImageSpan(Bitmap b, int verticalAlignment) {
        super(b, verticalAlignment);
    }

    public EmojiImageSpan(Context context, Bitmap b) {
        super(context, b);
    }

    public EmojiImageSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    public EmojiImageSpan(Drawable d) {
        super(d);
    }

    public EmojiImageSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

    public EmojiImageSpan(Drawable d, String source) {
        super(d, source);
    }

    public EmojiImageSpan(Drawable d, String source, int verticalAlignment) {
        super(d, source, verticalAlignment);
    }

    public EmojiImageSpan(Context context, Uri uri) {
        super(context, uri);
    }

    public EmojiImageSpan(Context context, Uri uri, int verticalAlignment) {
        super(context, uri, verticalAlignment);
    }

    public EmojiImageSpan(Context context, @DrawableRes int resourceId) {
        super(context, resourceId);
    }

    public EmojiImageSpan(Context context, @DrawableRes int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        Paint.FontMetricsInt fm = paint.getFontMetricsInt();
        int transY = (y + fm.descent + y + fm.ascent) / 2 - b.getBounds().bottom / 2;
        canvas.save();
        canvas.translate(x, transY);
        b.draw(canvas);
        canvas.restore();
    }
}

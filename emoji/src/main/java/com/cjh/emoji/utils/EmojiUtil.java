package com.cjh.emoji.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.cjh.emoji.EmojiBean;
import com.cjh.emoji.EmojiParentBean;
import com.cjh.emoji.EmojiPathBean;
import com.cjh.emoji.EmojiImageSpan;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianhui on 2017/3/6.
 */

public class EmojiUtil {
    private Context mContext;


    private List<EmojiParentBean> mListParent = new ArrayList<EmojiParentBean>();


    private static final EmojiUtil ourInstance = new EmojiUtil();
    private int emojiSize;

    public static EmojiUtil getInstance() {
        return ourInstance;
    }

    private EmojiUtil() {

    }

    public void init(Context context) {
        if (mListParent.size() != 0) {
            return;
        }
        mContext = context;
        emojiSize = Utils.dpToPx(24, mContext.getResources());

        final AssetManager assets = context.getAssets();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<EmojiPathBean> mListEmojiPathBeen = new ArrayList<EmojiPathBean>();
                String pathRoot = "emoji";
                try {
                    String[] listRoot = assets.list(pathRoot);
                    for (int i = 0; i < listRoot.length; i++) {
                        mListEmojiPathBeen = new ArrayList<EmojiPathBean>();
                        EmojiParentBean parentBean = new EmojiParentBean();
                        parentBean.setName(listRoot[i]);
                        parentBean.setPath(pathRoot + "/" + listRoot[i]);
                        String[] emojis = assets.list(parentBean.getPath());
                        for (int j = 0; j < emojis.length; j++) {
                            mListEmojiPathBeen.add(new EmojiPathBean(parentBean.getPath() + "/" + emojis[j], emojis[j].substring(0, emojis[j].indexOf("."))));
                        }
                        parentBean.setEmojiBeen(mListEmojiPathBeen);
                        mListParent.add(parentBean);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                EmojiPathBean bean = new EmojiPathBean("back.png", "back");
                for (int i = 0; i < mListParent.size(); i++) {
                    mListParent.get(i).getEmojiBeen().add(8, bean);
                }
            }
        }).start();
    }

    public String getFacePath(Context context, String type) {
        if (mListParent.size() == 0) {
            throw new IllegalArgumentException("Parameters is not ready");
        }
        String path = "";
        for (int i = 0; i < mListParent.size(); i++) {
            for (int j = 0; j < mListParent.get(i).getEmojiBeen().size(); j++) {
                EmojiPathBean pathBean = mListParent.get(i).getEmojiBeen().get(j);
                if (pathBean.getName().equals(type)) {
                    path = pathBean.getPath();
                    return path;
                }
            }
        }
        return path;
    }

    private static List<EmojiBean> findFace(String content) {
        List<EmojiBean> emojiList = new ArrayList<EmojiBean>();
        String[] split = content.split("《");
        int lastIndex = 0;
        for (int i = 0; i < split.length - 1; i++) {
            int start = content.indexOf("《", lastIndex);
            int end = content.indexOf("》", start);
            lastIndex = end;
            EmojiBean bean = new EmojiBean();
            bean.setStart(start);
            bean.setEnd(end);
            emojiList.add(bean);
        }
        for (int i = 0; i < emojiList.size(); i++) {
            EmojiBean emojiBean = emojiList.get(i);
            emojiList.get(i).setType(content.substring(emojiBean.getStart() + 1, emojiBean.getEnd()));
        }
        return emojiList;
    }

    public void loadTextView(TextView textView) {
        String string = textView.getText().toString();
        AssetManager mAssetManager = mContext.getAssets();
        List<EmojiBean> emojiList = findFace(string);
        if (emojiList.size() == 0) {
            return;
        }
        SpannableString spannable = new SpannableString(string);
        for (int i = 0; i < emojiList.size(); i++) {
            EmojiBean bean = emojiList.get(i);
            String urlPath = getFacePath(mContext, bean.getType());
            try {
                InputStream inputStream = mAssetManager.open(urlPath);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
                Drawable drawable = bitmapDrawable;
                drawable.setBounds(0, 0, emojiSize, emojiSize);
                EmojiImageSpan span = new EmojiImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                spannable.setSpan(span, bean.getStart(), bean.getEnd() + 1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        textView.setText(spannable);
    }

    public List<EmojiParentBean> getListParent() {
        return mListParent;
    }
}

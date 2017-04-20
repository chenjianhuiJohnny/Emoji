package com.cjh.emoji;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianhui on 2017/3/3.
 */

public class EmojiPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<EmojiParentBean> mListParent = new ArrayList<EmojiParentBean>();
    private EmojiAdapter.ClickListener mListener;

    public EmojiPagerAdapter(Context context, List<EmojiParentBean> listParent,EmojiAdapter.ClickListener listener) {
        mContext = context;
        mListParent = listParent;
        mListener = listener;
    }

    @Override
    public int getCount() {
        return mListParent.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RecyclerView recyclerView = new RecyclerView(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 9);
        recyclerView.setLayoutManager(gridLayoutManager);
        EmojiAdapter adapter = new EmojiAdapter(mContext, mListParent.get(position).getEmojiBeen());
        if (mListener != null) {
            adapter.setOnClickListener(mListener);
        }
        recyclerView.setAdapter(adapter);
        container.addView(recyclerView, params);
        return recyclerView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setOnRVClickListener(EmojiAdapter.ClickListener listener) {
        mListener = listener;
    }
}

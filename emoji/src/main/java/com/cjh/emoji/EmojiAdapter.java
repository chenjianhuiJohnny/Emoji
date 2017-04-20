package com.cjh.emoji;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cjh.emoji.utils.Utils;

import java.io.IOException;
import java.util.List;

/**
 * Created by chenjianhui on 2017/3/1.
 */

public class EmojiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<EmojiPathBean> mList;
    private LayoutInflater mInflater;
    private AssetManager mAssetManager;
    private int mImgId = 1234789;
    private ClickListener mListener;

    public EmojiAdapter(Context context, List<EmojiPathBean> list) {
        mContext = context;
        mList = list;
        mInflater = LayoutInflater.from(mContext);
        mAssetManager = mContext.getAssets();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout = new LinearLayout(mContext);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linearLayout.setBackgroundColor(Color.WHITE);
        params.gravity = Gravity.CENTER;
        linearLayout.setLayoutParams(params);
        ImageView imageView = new ImageView(mContext);
        LinearLayout.LayoutParams paramsImg = new LinearLayout.LayoutParams(Utils.dpToPx(38, mContext.getResources()), Utils.dpToPx(30, mContext.getResources()));
        imageView.setPadding(Utils.dpToPx(6, mContext.getResources()), 0, Utils.dpToPx(6, mContext.getResources()), 0);
        imageView.setId(mImgId);
        imageView.setLayoutParams(paramsImg);
        linearLayout.addView(imageView);
        return new ViewHolder(linearLayout);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        final EmojiPathBean bean = mList.get(position);

        try {
            Bitmap bm = BitmapFactory.decodeStream(mAssetManager.open(bean.getPath()));
            viewHolder.img.setImageBitmap(bm);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (mListener == null) {
            return;
        }

        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onClick(bean);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnClickListener(ClickListener listener) {
        mListener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;

        public ViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(mImgId);
        }
    }

    public interface ClickListener {
        void onClick(EmojiPathBean path);
    }
}

package com.cjh.emoji.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cjh.emoji.R;
import com.cjh.emoji.utils.Utils;

/**
 * Created by chenjianhui on 2017/3/3.
 */

public class EmojiFooterView extends RelativeLayout implements View.OnClickListener {

    private ImageView mImgFace;
    private ImageView mImgPeople;
    private ImageView mImgFood;
    private ImageView mImgNature;
    private ImageView mImgPlace;
    private ImageView mImgObject;
    private TextView mTvBack;

    private int mImgFaceID = 5445;
    private int mImgPeopleID = 5446;
    private int mImgFoodID = 5447;
    private int mImgNatureID = 5448;
    private int mImgPlaceID = 5449;
    private int mImgObjectID = 5440;
    private int mTvBackID = 5441;
    private onClickStyleListener mListener;

    public EmojiFooterView(Context context) {
        this(context, null);
    }

    public EmojiFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiFooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {

        int mPadding = Utils.dpToPx(4, getResources());
        int mSize = Utils.dpToPx(28, getResources());

        RelativeLayout.LayoutParams paramsFace = new LayoutParams(mSize, mSize);
        mImgFace = new ImageView(context);
        mImgFace.setPadding(mPadding, mPadding, mPadding, mPadding);
        mImgFace.setLayoutParams(paramsFace);
        mImgFace.setImageResource(R.mipmap.ic_face);
        mImgFace.setId(mImgFaceID);

        RelativeLayout.LayoutParams paramsPeople = new LayoutParams(mSize, mSize);
        paramsPeople.addRule(END_OF, mImgFaceID);
        mImgPeople = new ImageView(context);
        mImgPeople.setPadding(mPadding, mPadding, mPadding, mPadding);
        mImgPeople.setLayoutParams(paramsPeople);
        mImgPeople.setImageResource(R.mipmap.ic_people);
        mImgPeople.setId(mImgPeopleID);

        RelativeLayout.LayoutParams paramsFood = new LayoutParams(mSize, mSize);
        paramsFood.addRule(END_OF, mImgPeopleID);
        mImgFood = new ImageView(context);
        mImgFood.setPadding(mPadding, mPadding, mPadding, mPadding);
        mImgFood.setLayoutParams(paramsFood);
        mImgFood.setImageResource(R.mipmap.ic_food);
        mImgFood.setId(mImgFoodID);


        RelativeLayout.LayoutParams paramsNature = new LayoutParams(mSize, mSize);
        paramsNature.addRule(END_OF, mImgFoodID);
        mImgNature = new ImageView(context);
        mImgNature.setPadding(mPadding, mPadding, mPadding, mPadding);
        mImgNature.setLayoutParams(paramsNature);
        mImgNature.setImageResource(R.mipmap.ic_nature);
        mImgNature.setId(mImgNatureID);

        RelativeLayout.LayoutParams paramsPlace = new LayoutParams(mSize, mSize);
        paramsPlace.addRule(END_OF, mImgNatureID);
        mImgPlace = new ImageView(context);
        mImgPlace.setPadding(mPadding, mPadding, mPadding, mPadding);
        mImgPlace.setLayoutParams(paramsPlace);
        mImgPlace.setImageResource(R.mipmap.ic_place);
        mImgPlace.setId(mImgPlaceID);

        RelativeLayout.LayoutParams paramsObject = new LayoutParams(mSize, mSize);
        paramsObject.addRule(END_OF, mImgPlaceID);
        mImgObject = new ImageView(context);
        mImgObject.setPadding(mPadding, mPadding, mPadding, mPadding);
        mImgObject.setLayoutParams(paramsObject);
        mImgObject.setImageResource(R.mipmap.ic_object);
        mImgObject.setId(mImgObjectID);

        addView(mImgFace);
        addView(mImgPeople);
        addView(mImgFood);
        addView(mImgNature);
        addView(mImgPlace);
        addView(mImgObject);


        RelativeLayout.LayoutParams paramsBack = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsBack.addRule(ALIGN_PARENT_RIGHT, TRUE);
        paramsBack.addRule(CENTER_VERTICAL, TRUE);
        mTvBack = new TextView(context);
        mTvBack.setId(mTvBackID);
        mTvBack.setPadding(mPadding, mPadding, mPadding, mPadding);
        mTvBack.setText("返回");
        mTvBack.setTextColor(Color.argb(255, 219, 219, 219));
        mTvBack.setLayoutParams(paramsBack);

        addView(mTvBack);

        mImgFace.setOnClickListener(this);
        mImgPeople.setOnClickListener(this);
        mImgFood.setOnClickListener(this);
        mImgNature.setOnClickListener(this);
        mImgPlace.setOnClickListener(this);
        mImgObject.setOnClickListener(this);
        mTvBack.setOnClickListener(this);

    }


    public void setOnClickStyleListener(onClickStyleListener listener) {
        mListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mListener == null) {
            return;
        }
        switch (view.getId()) {
            case 5445:
                /*Face*/
                mListener.onClick(0);
                break;
            case 5446:
                /*People*/
                mListener.onClick(1);
                break;
            case 5447:
                /*Food*/
                mListener.onClick(2);
                break;
            case 5448:
                /*Nature*/
                mListener.onClick(3);
                break;
            case 5449:
                /*Place*/
                mListener.onClick(4);
                break;
            case 5440:
                /*Object*/
                mListener.onClick(5);
                break;
            case 5441:
                /*Back*/
                mListener.onClick(6);
                break;
        }
    }

    public interface onClickStyleListener {
        void onClick(int style);
    }


}

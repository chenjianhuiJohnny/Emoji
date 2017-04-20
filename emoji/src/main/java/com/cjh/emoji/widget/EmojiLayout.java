package com.cjh.emoji.widget;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjh.emoji.EmojiAdapter;
import com.cjh.emoji.EmojiBean;
import com.cjh.emoji.EmojiImageSpan;
import com.cjh.emoji.EmojiPagerAdapter;
import com.cjh.emoji.EmojiParentBean;
import com.cjh.emoji.EmojiPathBean;
import com.cjh.emoji.R;
import com.cjh.emoji.utils.EmojiUtil;
import com.cjh.emoji.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenjianhui on 2017/3/2.
 */

public class EmojiLayout extends LinearLayout implements EmojiAdapter.ClickListener, TextWatcher {
    private ImageView mImgDownBtn;
    private ViewPager mViewPager;
    private EmojiIteratorView mIteratorView;
    private EmojiFooterView mFooterView;
    private List<EmojiParentBean> mListParent = new ArrayList<EmojiParentBean>();
    private PagerAdapter mAdapterViewPager;
    private Context mContext;
    private EditText mEditText;
    private AssetManager mAssetManager;
    /**
     * 是否设置完毕
     */
    private boolean setting;
    private TextView mTextView;

    final View o = this;
    private int emojiSize;

    public EmojiLayout(Context context) {
        this(context, null);
    }

    public EmojiLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmojiLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {


        mContext = context;
        mAssetManager = context.getAssets();
        /*背景设置白色*/
        setBackgroundColor(Color.WHITE);
        /*添加顶部隐藏按钮*/
        int downBtnSize = Utils.dpToPx(26, getResources());
        int downBtnPadding = Utils.dpToPx(2, getResources());
        LayoutParams paramsDownBtn = new LayoutParams(downBtnSize * 2, downBtnSize);
        mImgDownBtn = new ImageView(context);
        mImgDownBtn.setLayoutParams(paramsDownBtn);
        mImgDownBtn.setPadding(0, downBtnPadding, 0, downBtnPadding);
        mImgDownBtn.setImageResource(R.mipmap.ic_down);
        mImgDownBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ObjectAnimator translationY = ObjectAnimator.ofFloat(o, "translationY", 0, getMeasuredHeight()).setDuration(300);
                translationY.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        o.setVisibility(GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                translationY.start();

            }
        });
        addView(mImgDownBtn);


        /*添加表情区域*/
        int viewPagerHeight = Utils.dpToPx(180, getResources());
        LayoutParams paramsViewPager = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, viewPagerHeight);
        mViewPager = new ViewPager(context);
        mViewPager.setLayoutParams(paramsViewPager);
        initViewPagerData(context);
        mViewPager.setOffscreenPageLimit(6);
        mAdapterViewPager = new EmojiPagerAdapter(context, mListParent, this);
        mViewPager.setAdapter(mAdapterViewPager);
        addView(mViewPager);
        setViewPagerEvent();

        /*添加小圆圈*/
        int IteratorMargin = Utils.dpToPx(4, getResources());
        LayoutParams paramsIterator = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsIterator.setMargins(0, IteratorMargin, 0, IteratorMargin);
        mIteratorView = new EmojiIteratorView(context);
        mIteratorView.setLayoutParams(paramsIterator);
        mIteratorView.setFocusItem(1);
        addView(mIteratorView);



        /*添加底部信息*/
        LayoutParams paramsFooter = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mFooterView = new EmojiFooterView(context);
        mFooterView.setLayoutParams(paramsFooter);
        addView(mFooterView);
        mFooterView.setOnClickStyleListener(new EmojiFooterView.onClickStyleListener() {
            @Override
            public void onClick(int style) {
                switch (style) {
                    case 0:
                        mViewPager.setCurrentItem(0);
                        break;
                    case 1:
                        mViewPager.setCurrentItem(1);
                        break;
                    case 2:
                        mViewPager.setCurrentItem(2);
                        break;
                    case 3:
                        mViewPager.setCurrentItem(3);
                        break;
                    case 4:
                        mViewPager.setCurrentItem(4);
                        break;
                    case 5:
                        mViewPager.setCurrentItem(5);
                        break;
                    case 6:
                        /**
                         * 返回按钮
                         */
                        final InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                        if (imm.isActive(mEditText)) {
                            o.setVisibility(GONE);
                            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                        } else {
                            hide();
                        }

                        break;
                }
            }
        });


        emojiSize = Utils.dpToPx(24, mContext.getResources());


    }

    private void setViewPagerEvent() {
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mIteratorView.setFocusItem(position + 1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initViewPagerData(Context context) {
        mListParent = EmojiUtil.getInstance().getListParent();
    }

    int currentSelection = 0;


    public void setEditText(EditText editText) {
        mEditText = editText;
        mEditText.addTextChangedListener(this);
        mEditText.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (o.getVisibility() == VISIBLE) {
                    o.setVisibility(GONE);
                }
                return false;
            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onClick(EmojiPathBean path) {
        if (path.getName().equals("back")) {
            doBack();
            return;
        }
        String faceName = "《" + path.getName() + "》" + " ";
        int startIndex = mEditText.getSelectionStart();
        /*mEditText.setText(mEditText.getText().toString() + faceName);*/
        mEditText.getText().insert(startIndex, faceName);
        currentSelection = startIndex + faceName.length();
        mEditText.setSelection(currentSelection);
    }

    private static final String TAG = "EmojiLayout";

    private void doBack() {
        int keyCode = KeyEvent.KEYCODE_DEL;
        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
        String string = mEditText.getText().toString();
        int space = string.lastIndexOf(" ");
        int word = string.lastIndexOf("》");
        if (space == string.length() - 1 && word == string.length() - 2) {
            mEditText.dispatchKeyEvent(keyEvent);
        }
        mEditText.dispatchKeyEvent(keyEvent);
    }

    @Override
    public void afterTextChanged(Editable editable) {
        if (editable == null && editable.length() == 0) {
            return;
        }
        if (setting) {
            setting = false;
            return;
        }
        String content = editable.toString();
        List<EmojiBean> emojiList = findFace(content);
        SpannableString spannable = new SpannableString(content);
        if (emojiList.size() == 0) {
            setting = true;
            int selectionEnd = mEditText.getSelectionStart();
            mEditText.setText(spannable);
            mEditText.setSelection(selectionEnd);
            return;
        }

        for (int i = 0; i < emojiList.size(); i++) {
            EmojiBean bean = emojiList.get(i);
            String urlPath = getFacePath(bean.getType());
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
        setting = true;
        int selectionEnd = mEditText.getSelectionEnd();
        mEditText.setText(spannable);
        mEditText.setSelection(selectionEnd);

    }

    private String getFacePath(String type) {
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

    private List<EmojiBean> findFace(String content) {
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

    public void setTextView(TextView textView) {
        mTextView = textView;
    }


    public void show() {
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditText.getWindowToken(), 0);
        if (this.getVisibility() == VISIBLE) {
            return;
        }

        final ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", getMeasuredHeight(), 0).setDuration(300);
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                o.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animator) {

            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        postDelayed(new Runnable() {
            @Override
            public void run() {
                translationY.start();
            }
        }, 100);

    }

    public void hide() {
        if (this.getVisibility() == GONE) {
            return;
        }
        ObjectAnimator translationY = ObjectAnimator.ofFloat(this, "translationY", 0, getMeasuredHeight()).setDuration(300);
        translationY.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                o.setVisibility(GONE);
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        translationY.start();

    }
}



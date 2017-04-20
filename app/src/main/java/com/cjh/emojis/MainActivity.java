package com.cjh.emojis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.cjh.emoji.widget.EmojiLayout;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EmojiLayout mEmojiLayout;
    private EditText mEditText;
    private ImageView mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEmojiLayout = (EmojiLayout) findViewById(R.id.emoji_layout);
        mBtn = (ImageView) findViewById(R.id.btn);
        mEditText = (EditText) findViewById(R.id.et);
        mEmojiLayout.setEditText(mEditText);

        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        mEmojiLayout.show();
    }
}

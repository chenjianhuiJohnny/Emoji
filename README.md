# Emoji

自己写了一个Emoji表情输入

使用步骤：

1，在Application的onCreate()里面添加:
   EmojiUtil.getInstance().init(this)
   
2，在布局里面添加：
    <com.cjh.emoji.widget.EmojiLayout
        android:id="@+id/emoji_layout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:gravity="center_horizontal"
        android:orientation="vertical"
        android:visibility="gone"/>
        
 3，在MainActivity的onCreate使用:
     mEmojiLayout.setEditText(mEditText);
     
 
 备注：
      mEmojiLayout.show() 显示
      mEmojiLayout.hide() 隐藏
  

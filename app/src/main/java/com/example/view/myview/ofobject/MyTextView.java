package com.example.view.myview.ofobject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class MyTextView extends TextView{
    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setCharText(Character charText){
        setText(String.valueOf(charText));
    }

    public void setScale(float scale){
        setScaleX(scale);
        setScaleY(scale);
    }
}

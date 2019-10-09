package com.example.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import com.example.view.R;
import com.example.view.myview.ofobject.MyTextView;
import com.example.view.myview.ofobject.CharEvaluator;

public class PropertyActivity extends AppCompatActivity {
    ObjectAnimator propertyAnim;
    ObjectAnimator keyframeAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        MyTextView myTextView=findViewById(R.id.ofobject);
        final ImageView imageView=findViewById(R.id.iv_clock);
        Button property=findViewById(R.id.btn_start_property);
        final Button keyframe=findViewById(R.id.btn_start_keyframe);

        //字母动画
        PropertyValuesHolder propertyValuesHolder=PropertyValuesHolder
                .ofObject("CharText",new CharEvaluator(), 'A', 'Z');
        PropertyValuesHolder alpha=PropertyValuesHolder.ofFloat("alpha",1f,0.5f);
        propertyAnim =ObjectAnimator.ofPropertyValuesHolder(myTextView,propertyValuesHolder,alpha);
        propertyAnim.setDuration(3000);
        propertyAnim.setInterpolator(new AccelerateInterpolator());

        //闹钟动画
        Keyframe keyframe1=Keyframe.ofFloat(0,0);
        Keyframe keyframe2=Keyframe.ofFloat(0.1f,-10f);
        Keyframe keyframe3=Keyframe.ofFloat(0.2f,-20f);
        Keyframe keyframe4=Keyframe.ofFloat(0.3f,-10f);
        Keyframe keyframe5=Keyframe.ofFloat(0.4f,0);
        Keyframe keyframe6=Keyframe.ofFloat(0.5f,10f);
        Keyframe keyframe7=Keyframe.ofFloat(0.6f,20f);
        Keyframe keyframe8=Keyframe.ofFloat(0.7f,10f);
        Keyframe keyframe9=Keyframe.ofFloat(0.8f,0);
        Keyframe keyframe10=Keyframe.ofFloat(0.9f,-10f);
        Keyframe keyframe11=Keyframe.ofFloat(1f,0);
        PropertyValuesHolder key=PropertyValuesHolder
                .ofKeyframe("rotation",keyframe1,keyframe2,keyframe3,keyframe4,keyframe5,keyframe6,keyframe7,keyframe8,keyframe9,keyframe10,keyframe11);
        PropertyValuesHolder scale=PropertyValuesHolder.ofFloat("ScaleX",1f,1.1f,1.2f,1.1f,1f);
        keyframeAnim=ObjectAnimator.ofPropertyValuesHolder(imageView,key,scale);
        keyframeAnim.setRepeatCount(ValueAnimator.INFINITE);

        property.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertyAnim.start();
            }
        });

        keyframe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                keyframeAnim.start();
            }
        });

        keyframe.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                keyframeAnim.cancel();
                return true;
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        propertyAnim.cancel();
        keyframeAnim.cancel();
    }
}

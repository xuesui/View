package com.example.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.view.R;

public class PathActivity extends AppCompatActivity {
    private boolean menuIsOpen = false;
    private Button item1;
    private Button item2;
    private Button item3;
    private Button item5;
    private Button item4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        Button menu = findViewById(R.id.menu);
        item1=findViewById(R.id.item1);
        item2=findViewById(R.id.item2);
        item3=findViewById(R.id.item3);
        item4=findViewById(R.id.item4);
        item5=findViewById(R.id.item5);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuIsOpen == false) {
                    openMenu();
                    menuIsOpen = true;
                } else if (menuIsOpen == true) {
                    closeMenu();
                    menuIsOpen = false;
                }
            }
        });
    }

    private void openMenu() {
        doAnimateOpen(item1,0,5,300);
        doAnimateOpen(item2,1,5,300);
        doAnimateOpen(item3,2,5,300);
        doAnimateOpen(item4,3,5,300);
        doAnimateOpen(item5,4,5,300);
    }

    private void closeMenu() {
        doAnimateClose(item1,0,5,300);
        doAnimateClose(item2,1,5,300);
        doAnimateClose(item3,2,5,300);
        doAnimateClose(item4,3,5,300);
        doAnimateClose(item5,4,5,300);
    }

    private void doAnimateOpen(View view,int index,int total,int radius) {
        if (view.getVisibility()!=View.VISIBLE){
            view.setVisibility(View.VISIBLE);
        }
        double degree=Math.toRadians(90)/(total-1) *index;
        int translationX= -(int) (radius *Math.sin(degree));
        int translationY= -(int) (radius*Math.cos(degree));
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",0,translationX),
                ObjectAnimator.ofFloat(view,"translationY",0,translationY),
                ObjectAnimator.ofFloat(view,"scaleX",0f,1f),
                ObjectAnimator.ofFloat(view,"scaleY",0f,1f),
                ObjectAnimator.ofFloat(view,"alpha",0f,1)
        );
        animatorSet.setDuration(500).start();
    }

    private void doAnimateClose(final View view, int index, int total, int radius) {
        double degree=Math.PI *index/((total-1)*2);
        int translationX= -(int) (radius *Math.sin(degree));
        int translationY= -(int) (radius*Math.cos(degree));
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(view,"translationX",translationX,0),
                ObjectAnimator.ofFloat(view,"translationY",translationY,0),
                ObjectAnimator.ofFloat(view,"scaleX",1f,0.1f),
                ObjectAnimator.ofFloat(view,"scaleY",1f,0.1f),
                ObjectAnimator.ofFloat(view,"alpha",1,0f)
        );
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                if (view.getVisibility()!=View.GONE){
                    view.setVisibility(View.GONE);
                }
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.setDuration(500).start();
    }
}

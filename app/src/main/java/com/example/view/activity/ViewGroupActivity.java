package com.example.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.LayoutTransition;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.view.R;

public class ViewGroupActivity extends AppCompatActivity implements View.OnClickListener {
    private static int id = 0;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_group);
        linearLayout = findViewById(R.id.ll_viewgroup);
        Button add = findViewById(R.id.btn_add);
        Button delete = findViewById(R.id.btn_delete);

        add.setOnClickListener(this);
        delete.setOnClickListener(this);

        LayoutTransition transition=new LayoutTransition();
        ObjectAnimator animatorIn=ObjectAnimator.ofFloat(null,"translationX",600f,0f);
        ObjectAnimator animatorOut=ObjectAnimator.ofFloat(null,"translationX",0f,600f);
       ObjectAnimator animator=ObjectAnimator.ofFloat(null,"scaleX",0f,1f);
        transition.setAnimator(LayoutTransition.DISAPPEARING,animatorOut);
        transition.setAnimator(LayoutTransition.APPEARING,animator);
        linearLayout.setLayoutTransition(transition);
    }

    private void add() {
        id++;
        Button button = new Button(this);
        String string = "button" + id;
        button.setText(string);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        button.setLayoutParams(params);
        linearLayout.addView(button);
    }

    private void delete() {
        if (id > 0) {
            linearLayout.removeViewAt(0);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_add:
                add();
                break;
            case R.id.btn_delete:
                delete();
                break;
            default:
                break;
        }
    }
}

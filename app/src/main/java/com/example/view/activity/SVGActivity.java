package com.example.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.view.R;

public class SVGActivity extends AppCompatActivity {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_svg);
        final ImageView imageView = findViewById(R.id.iv_anim);

        final EditText editText=findViewById(R.id.edit);
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    AnimatedVectorDrawableCompat animatedVectorDrawableCompat=AnimatedVectorDrawableCompat
                            .create(SVGActivity.this,R.drawable.animated_vector_search);
                    imageView.setImageDrawable(animatedVectorDrawableCompat);
                    ((Animatable) imageView.getDrawable()).start();
                }
            }
        });
        
    }
}

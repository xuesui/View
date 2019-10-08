package com.example.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.view.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button buttonPath = findViewById(R.id.btn_path);
        Button buttonProperty = findViewById(R.id.btn_property);
        Button buttonViewGroup = findViewById(R.id.btn_viewgroup);

        buttonPath.setOnClickListener(this);
        buttonProperty.setOnClickListener(this);
        buttonViewGroup.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_path:
                Intent intentPath = new Intent(MainActivity.this, PathActivity.class);
                startActivity(intentPath);
                break;
            case R.id.btn_property:
                Intent intentProperty = new Intent(MainActivity.this, PropertyActivity.class);
                startActivity(intentProperty);
                break;
            case R.id.btn_viewgroup:
                Intent intentViewGroup = new Intent(MainActivity.this, ViewGroupActivity.class);
                startActivity(intentViewGroup);
                break;
            default:
                break;
        }
    }
}

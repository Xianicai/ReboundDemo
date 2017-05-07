package com.example.xianicai.rebounddemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public class SpringAnimationActivity extends AppCompatActivity {

    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spring_animation);
        mImageView = (ImageView) findViewById(R.id.image);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initeSpringAnimation();
            }
        });
    }

    private void initeSpringAnimation() {
        SpringSystem springSystem = SpringSystem.create();

// Add a spring to the system.
        Spring spring = springSystem.createSpring();

// Add a listener to observe the motion of the spring.
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(100,1));
        spring.addListener(new SimpleSpringListener() {

            @Override
            public void onSpringUpdate(Spring spring) {
                // You can observe the updates in the spring
                // state by asking its current value in onSpringUpdate.
                float value = (float) spring.getCurrentValue();
                float scale = 1f - (value * 0.5f);
                mImageView.setScaleX(scale);
                mImageView.setScaleY(scale);
            }
        });

// Set the spring in motion; moving from 0 to 1
        spring.setEndValue(1);
    }
}

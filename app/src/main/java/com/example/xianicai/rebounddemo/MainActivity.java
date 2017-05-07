package com.example.xianicai.rebounddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.rebound.SimpleSpringListener;
import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

public class MainActivity extends AppCompatActivity {
    private SpringSystem springSystem;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        springSystem = SpringSystem.create();
        final ImageView imageView = (ImageView) findViewById(R.id.image);
        mButton = (Button) findViewById(R.id.btn_next);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,SpringAnimationActivity.class));
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateViewDirection(imageView, 0f, 1f, 50,15);

            }
        });
        //创建一个弹簧系统
    }

    /**
     * 弹簧动画
     *
     * @param v        动画View
     * @param from     开始参数
     * @param to       结束参数
     * @param tension  拉力系数
     * @param friction 摩擦力系数
     */
    private void animateViewDirection(final View v, float from, float to, int tension, int friction) {
        //从弹簧系统创建一个弹簧
        Spring spring = springSystem.createSpring();
        //设置弹簧的开始参数
        spring.setCurrentValue(from);
        //查看源码可知
        //public static SpringConfig defaultConfig = fromOrigamiTensionAndFriction(40.0D, 7.0D);
        //弹簧的默认拉力是40，摩擦是7。这里设置为我们seekBar选择的拉力和摩擦参数
        spring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(tension, friction));
        //给弹簧添加监听，动态设置控件的状态
        spring.addListener(new SimpleSpringListener() {
            @Override
            public void onSpringUpdate(Spring spring) {
                //设置图片的X,Y的缩放
                //还可以设置setAlpha,setTranslationX...综合形成复杂的动画
                v.setScaleX((float) spring.getCurrentValue());
                v.setScaleY((float) spring.getCurrentValue());
            }
        });
        //设置结束时图片的参数
        spring.setEndValue(to);
    }
}

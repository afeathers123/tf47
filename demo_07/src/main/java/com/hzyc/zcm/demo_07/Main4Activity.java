package com.hzyc.zcm.demo_07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Main4Activity extends AppCompatActivity {

    private ImageView t1;
    private Button alpha;
    private Button scale;
    private Button translate;
    private Button rotate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        t1 = (ImageView) findViewById(R.id.t1);
        alpha = (Button) findViewById(R.id.alpha);
        scale = (Button) findViewById(R.id.scale);
        translate = (Button) findViewById(R.id.translate);
        rotate = (Button) findViewById(R.id.rotate);

        alpha.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.alpha);
                t1.startAnimation(alphaAnimation);
            }
        });

        scale.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.scale);
                t1.startAnimation(alphaAnimation);
            }
        });

        translate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.translate);
                t1.startAnimation(alphaAnimation);
            }
        });

        rotate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Animation alphaAnimation = AnimationUtils.loadAnimation(Main4Activity.this, R.anim.rotate);
                t1.startAnimation(alphaAnimation);
            }
        });
    }
}

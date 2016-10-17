package com.example.asus.kugoumusic.tmp;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.kugoumusic.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main6Activity extends AppCompatActivity {


    @BindView(R.id.text)
    View text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.text)
    public void text(View v) {
        Toast.makeText(this,"sssss",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.startAnim)
    public void start(View v) {
        Toast.makeText(this,"动画1",Toast.LENGTH_SHORT).show();
       /* TranslateAnimation animation = new TranslateAnimation(0,100,0,0);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        text.startAnimation(animation);*/

        ObjectAnimator animator = ObjectAnimator.ofFloat(text,"translationX",0,100);//注意ObjectAnimator
        animator.setDuration(1000);
        animator.start();


    }

    @OnClick(R.id.startAnim1)
    public void start1(View v) {
        Toast.makeText(this,"动画2",Toast.LENGTH_SHORT).show();

        ValueAnimator animator = new  ValueAnimator();//注意ValueAnimator

        animator.setDuration(1000);
        animator.setObjectValues(new PointF(0, 0));
        animator.setInterpolator(new OvershootInterpolator());//插值器

        animator.setEvaluator(new TypeEvaluator() {
            @Override
            public Object evaluate(float fraction, Object startValue, Object endValue) {
                PointF pointF = new PointF();
                pointF.x = 200 * fraction * 3;
                pointF.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return pointF;
            }
        });
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                text.setX(pointF.x);
                text.setY(pointF.y);
            }
        });

    }
}

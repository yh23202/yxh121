package com.yangxiaohe.yangshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

import com.yangxiaohe.yangshop.utils.Contants;
import com.yangxiaohe.yangshop.utils.PreferenceUtils;

/**
 * 作者：Administrator on 2017/10/5 22:59
 * 邮箱：281893416@qq.com
 */
public class SplashActivity extends AppCompatActivity {
    private static final long ANIMATION_DELAY =1500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
    }

    private void initView() {
        RelativeLayout mRlRoot= (RelativeLayout) findViewById(R.id.rl_splash_root);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.INFINITE, 0, 360, Animation.INFINITE);
        rotateAnimation.setDuration(ANIMATION_DELAY);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1f, 0, 1f, Animation.INFINITE, Animation.INFINITE);
        scaleAnimation.setDuration(ANIMATION_DELAY);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1.0f);
        alphaAnimation.setDuration(ANIMATION_DELAY);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        mRlRoot.setAnimation(animationSet);
        SplashAnimationListener splashAnimationListener = new SplashAnimationListener();
        animationSet.setAnimationListener(splashAnimationListener);

    }
   public class SplashAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            boolean isFirst = PreferenceUtils.getBoolean(SplashActivity.this, Contants.SPLASH_FIRST_USE);
            if (isFirst){
                startActivity(new Intent(SplashActivity.this,GuideActivity.class));
                //startActivity(new Intent(GuideActivity.class));
            }else {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
            }
            finish();
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    private void initData() {

    }
}

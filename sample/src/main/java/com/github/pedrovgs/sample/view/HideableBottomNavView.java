package com.github.pedrovgs.sample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.pedrovgs.sample.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HideableBottomNavView extends BottomNavigationView {
    public HideableBottomNavView(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        slideDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                HideableBottomNavView.this.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        slideUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                HideableBottomNavView.this.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public HideableBottomNavView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HideableBottomNavView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private Animation slideDown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
    private Animation slideUp = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);




    public void hide() {
        if(this.getVisibility() == View.VISIBLE) startAnimation(slideDown);
    }

    public void show(){
        if(this.getVisibility() != View.VISIBLE) startAnimation(slideUp);

    }
}

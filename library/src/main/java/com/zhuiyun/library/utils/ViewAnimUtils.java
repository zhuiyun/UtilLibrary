package com.inkbird.inkbirdapp.device.ibsth5.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

import com.inkbird.base.utils.CommonUtils;

public class ViewAnimUtils {
    // 显示动画
    public static void showViewWithAnim(View view, int height) {
        view.setVisibility(View.VISIBLE);
        ValueAnimator valueAnimator = createDropAnimator(view, 0, height);
        valueAnimator.setDuration(500);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
        objectAnimator.setDuration(500);


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator, objectAnimator);
        animatorSet.start();
    }


    // 隐藏动画，最后一个参数为动画结束的接口回调
    public static void hideViewWithAnim(View view, int height, AnimationEnd animationEnd) {
        ValueAnimator valueAnimator = createDropAnimator(view, height, 0);
        valueAnimator.setDuration(500);


        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "alpha", 1f, 0f);
        objectAnimator.setDuration(500);
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }


            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
                if(animationEnd != null)
                animationEnd.onAnimationEnd();
            }


            @Override
            public void onAnimationCancel(Animator animation) {
            }


            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(valueAnimator, objectAnimator);
        animatorSet.start();
    }

    public static void heightAnimator(View view,int start,int end){
        ValueAnimator valueAnimator = ViewAnimUtils.createDropAnimator(view, CommonUtils.dpToPx(view.getContext(),start), CommonUtils.dpToPx(view.getContext(),end));
        valueAnimator.setDuration(200);
        valueAnimator.start();
    }

    // 创建高度增减的属性动画
    public static ValueAnimator createDropAnimator(final View v, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator arg0) {
                int value = (int) arg0.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = v.getLayoutParams();
                layoutParams.height = value;
                v.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    public static void viewRotation(View view,float start,float end){
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "rotation", start, end);
        objectAnimator.setDuration(300).start();
    }

    public interface AnimationEnd{
        void onAnimationEnd();
    }
}
package com.pan.nurseStation.animate;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.view.View;

public class AnimateBusiness {
    public static void slideToggle(View view, float translationY, long duration1, long duration2) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, translationY);
        objectAnimator.addListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                new Handler().postDelayed(() -> slideUp(view, translationY, duration1), duration2);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        objectAnimator.setDuration(duration1).start();

//        AnimateBusiness.slideDown(view, 200, duration1);
//        new Handler().postDelayed(() -> AnimateBusiness.slideUp(view, 200, duration1), duration1 + duration2);
    }

    public static void slideDown(View view, float translationY, long duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", 0, translationY);
        objectAnimator.setDuration(duration).start();
    }

    public static void slideUp(View view, float translationY, long duration) {
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "translationY", translationY, 0);
        objectAnimator.setDuration(duration).start();
    }
}

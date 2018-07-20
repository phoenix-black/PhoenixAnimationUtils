package com.blackphoenix.phoenixanimationutils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;

/**
 * Created by Praba on 10/27/2017.
 */
public class PhoenixAnimationUtils  {

    public static Animation loadAnimation(@NonNull Context context, @NonNull PhoenixAnimationType animationType) throws NotFoundException {

        switch (animationType){
            case TOGETHER:
                return AnimationUtils.loadAnimation(context,R.anim.together);
            case SEQUENTIAL:
                return AnimationUtils.loadAnimation(context,R.anim.sequential);
            case FROM_MIDDLE:
                return AnimationUtils.loadAnimation(context,R.anim.flip_from_middle);
            case TO_MIDDLE:
                return AnimationUtils.loadAnimation(context,R.anim.flip_to_middle);
            case SLIDE_FROM_LEFT:
                return AnimationUtils.loadAnimation(context,R.anim.slide_from_left);
            case SLIDE_FROM_RIGHT:
                return AnimationUtils.loadAnimation(context,R.anim.slide_from_right);
            case PENDULUM:
                return AnimationUtils.loadAnimation(context,R.anim.pendulum);
            case PULSE:
                return AnimationUtils.loadAnimation(context,R.anim.pulse);
            case FADE_IN:
                return AnimationUtils.loadAnimation(context,R.anim.fade_in);
            case FADE_OUT:
                return AnimationUtils.loadAnimation(context,R.anim.fade_out);
            case ROTATION_INFINITE:
                return animateRotation(-1,2000);

            default:
                NotFoundException errorExcep = new NotFoundException("Unknown Animation Type: "+animationType.toString());
                errorExcep.initCause(new Throwable(""+animationType.toString()+ " - PhoenixAnimationType is either empty or not matched with existing. "));
                throw errorExcep;
        }

    }



    public static RotateAnimation animateRotation(int repeat, long duration){
        RotateAnimation animRotation = new RotateAnimation(0.0F, 360.0F, 1, 0.5F, 1, 0.5F);
        animRotation.setRepeatCount(repeat);
        animRotation.setInterpolator(new LinearInterpolator());
        animRotation.setDuration(duration);
        return animRotation;
    }

    /*
    Flip Animation
    Using From Middle and to Middle Motion
    @Parm
    * Context context
    * View view
    * long durationMilliS (In milliseconds)
    */

    public static void animateFlip(final Context context, final View view, long durationMilliS){
        Animation animationStart = loadAnimation(context,PhoenixAnimationType.TO_MIDDLE);
        final Animation animationEnd = loadAnimation(context,PhoenixAnimationType.FROM_MIDDLE);

        if(durationMilliS!=-1) {
            animationStart.setDuration(durationMilliS);
            animationEnd.setDuration(durationMilliS);
        }

        animationStart.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.startAnimation(animationEnd);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animationStart);
    }


    /*
    Flip Animation
    Using From Middle and to Middle Motion
    @Parm
    * Context context
    * View view
    */


    public static void animateFlip(final Context context, final View view) {
        animateFlip(context,view,-1);
    }


    /**
     *
     * @param view
     * @param durationInMs
     * @param repeatCount
     */

    public static Animator animateFlipRotationHorizontal(View view, int durationInMs, int repeatCount){
       return animateFlipRotation(view,durationInMs,repeatCount,PhoenixFlipRotationDirection.HORIZONTAL);
    }

    /**
     *
     * @param view
     * @param durationInMs
     * @param repeatCount
     * @param listenerAdapter
     * @return
     */
    public static Animator animateFlipRotationHorizontal(View view, int durationInMs, int repeatCount, AnimatorListenerAdapter listenerAdapter){
        return animateFlipRotation(view,durationInMs,repeatCount,PhoenixFlipRotationDirection.HORIZONTAL,listenerAdapter);
    }

    /**
     *
     * @param view
     * @param durationInMs
     * @param repeatCount
     */

    public static Animator animateFlipRotationVertical(View view, int durationInMs, int repeatCount){
       return animateFlipRotation(view,durationInMs,repeatCount,PhoenixFlipRotationDirection.VERTICAL);
    }

    /**
     *
     * @param view
     * @param durationInMs
     * @param repeatCount
     * @param listenerAdapter
     * @return
     */
    public static Animator animateFlipRotationVertical(View view, int durationInMs, int repeatCount, AnimatorListenerAdapter listenerAdapter){
        return animateFlipRotation(view,durationInMs,repeatCount,PhoenixFlipRotationDirection.VERTICAL,listenerAdapter);
    }

    /**
     *
     * @param view
     * @param duration
     * @param repeatCount
     * @param rotationDirection
     */

    public static Animator animateFlipRotation(View view, int duration, int repeatCount, PhoenixFlipRotationDirection rotationDirection){
        return animateFlipRotation(view,duration,repeatCount,rotationDirection,null);
    }


    /**
     *
     * @param view
     * @param duration
     * @param repeatCount
     * @param rotationDirection
     * @param animatorListenerAdapter
     * @return
     */
    public static Animator animateFlipRotation(View view, int duration, int repeatCount, PhoenixFlipRotationDirection rotationDirection, AnimatorListenerAdapter animatorListenerAdapter){
        ObjectAnimator flip = ObjectAnimator.ofFloat(view, rotationDirection.toString(), 0f, 360f);
        flip.setDuration(duration);
        flip.setInterpolator(new LinearInterpolator());
        flip.setRepeatCount(repeatCount);
        if(animatorListenerAdapter!=null) {
            flip.addListener(animatorListenerAdapter);
        }
        flip.start();
        return flip;
    }

    /*
    * Pendulum Animation
    *
     */

    public static void animatePendulum(final Context context, View view){
        view.startAnimation(loadAnimation(context,PhoenixAnimationType.PENDULUM));
    }

    public static void animatePendulum(final Context context, View view, long durationMilliS){
        Animation pendulumAnimation = loadAnimation(context,PhoenixAnimationType.PENDULUM);
        pendulumAnimation.setDuration(durationMilliS);
        view.startAnimation(pendulumAnimation);
    }

    public static void animatePendulum(final Context context, View view, int repeatCount){
        Animation pendulumAnimation = loadAnimation(context,PhoenixAnimationType.PENDULUM);
        pendulumAnimation.setRepeatCount(repeatCount);
        view.startAnimation(pendulumAnimation);
    }

    public static void animatePendulum(final Context context, View view, long durationMilliS, int repeatCount){
        animatePendulum(context,view,durationMilliS,repeatCount,null);
    }

    public static void animatePendulum(final Context context, View view, long durationMilliS, int repeatCount, Animation.AnimationListener listener){
        Animation pendulumAnimation = loadAnimation(context,PhoenixAnimationType.PENDULUM);
        pendulumAnimation.setDuration(durationMilliS);
        pendulumAnimation.setRepeatCount(repeatCount);
        if(listener!=null) {
            pendulumAnimation.setAnimationListener(listener);
        }
        view.startAnimation(pendulumAnimation);
    }

    }


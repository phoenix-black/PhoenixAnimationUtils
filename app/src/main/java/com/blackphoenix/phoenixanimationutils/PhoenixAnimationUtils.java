package com.blackphoenix.phoenixanimationutils;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

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
            default:
                NotFoundException errorExcep = new NotFoundException("Unknown Animation Type: "+animationType.toString());
                errorExcep.initCause(new Throwable(""+animationType.toString()+ " - PhoenixAnimationType is either empty or not matched with existing. "));
                throw errorExcep;
        }

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
        Animation pendulumAnimation = loadAnimation(context,PhoenixAnimationType.PENDULUM);
        pendulumAnimation.setDuration(durationMilliS);
        pendulumAnimation.setRepeatCount(repeatCount);
        view.startAnimation(pendulumAnimation);
    }

    }


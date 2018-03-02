package com.blackphoenix.phoenixanimationutils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by Praba on 10/26/2017.
 */
public class PhoenixAnimation {

    public static Animation FromMiddle(Context context){
        return AnimationUtils.loadAnimation(context,R.anim.flip_from_middle);
    }

    public static Animation ToMiddle(Context context){
        return AnimationUtils.loadAnimation(context,R.anim.flip_to_middle);
    }
}

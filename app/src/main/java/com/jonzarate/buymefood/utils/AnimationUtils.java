package com.jonzarate.buymefood.utils;

import android.support.annotation.IdRes;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.transition.TransitionManager;

import com.jonzarate.buymefood.R;

/**
 * Created by JonZarate on 13/03/2018.
 */

public class AnimationUtils {

    public static void setViewVisibility (ConstraintLayout layout, @IdRes int viewId, int visibility) {
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);
        set.setVisibility(viewId, visibility);

        TransitionManager.beginDelayedTransition(layout);
        set.applyTo(layout);
    }

}

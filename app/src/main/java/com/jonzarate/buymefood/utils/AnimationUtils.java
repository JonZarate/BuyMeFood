package com.jonzarate.buymefood.utils;

import androidx.annotation.IdRes;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.transition.TransitionManager;

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

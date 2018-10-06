package com.jonzarate.buymefood.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class PaddinglessProgressBar extends ProgressBar {


    public PaddinglessProgressBar(Context context) {
        this(context, null);
    }

    public PaddinglessProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaddinglessProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PaddinglessProgressBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);



    }

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        setPadding(0, 0, 0, 0);

        ViewGroup.LayoutParams params = getLayoutParams();
        if (params instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) params;
            marginParams.topMargin = 0;
            marginParams.bottomMargin = 0;
            setLayoutParams(marginParams);
        }

         super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}

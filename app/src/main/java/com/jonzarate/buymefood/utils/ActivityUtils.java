package com.jonzarate.buymefood.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class ActivityUtils {


    public static void initToolbar(AppCompatActivity activity, @IdRes int toolbarId) {
        initToolbar(activity, toolbarId, false);
    }

    public static void initToolbar(AppCompatActivity activity, @IdRes int toolbarId, boolean showBackAsUpEnabled){
        Toolbar toolbar = activity.findViewById(toolbarId);
        if (toolbar != null) {
            activity.setSupportActionBar(toolbar);
            ActionBar ab = activity.getSupportActionBar();
            if (ab != null) {
                ab.setDisplayHomeAsUpEnabled(showBackAsUpEnabled);
            }
        }
    }

    public static void addFragmentToActivity (@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, int frameId) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void hideKeyboard(Context context, View view){
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


}

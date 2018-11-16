package com.jonzarate.buymefood.utils;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * Created by JonZarate on 26/02/2018.
 */

public class ActivityUtils {


    public static void initToolbar(AppCompatActivity activity, @IdRes int toolbarId) {
        initToolbar(activity, toolbarId, false);
    }

    public static void initToolbar(
            AppCompatActivity activity, @IdRes int toolbarId, boolean showBackAsUpEnabled){

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
                                              @NonNull Fragment fragment,
                                              int frameId) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void hideKeyboard(Context context, View view){
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            IBinder token = view.getWindowToken();
            imm.hideSoftInputFromWindow(token, 0);
        }
    }


}

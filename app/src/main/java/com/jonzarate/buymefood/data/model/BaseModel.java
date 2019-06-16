package com.jonzarate.buymefood.data.model;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import androidx.annotation.NonNull;

/**
 * Created by JonZarate on 15/03/2018.
 */

@IgnoreExtraProperties
public class BaseModel {

    @Exclude
    private String id;

    public <T extends BaseModel> T withId(@NonNull final String id) {
        this.id = id;
        return (T) this;
    }

    public String getId() {
        return id;
    }
}
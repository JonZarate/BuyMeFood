package com.jonzarate.buymefood

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings

class BuyMeFood : Application() {

    override fun onCreate() {
        super.onCreate()

        setupFirebase()
    }

    fun setupFirebase() {

        FirebaseApp.initializeApp(this)

        /* Due to forthcoming changes in Firebase, we need to setup how we handle dates */

        val firestore : FirebaseFirestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        firestore.firestoreSettings = settings
    }
}
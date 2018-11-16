package com.jonzarate.buymefood

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.jonzarate.buymefood.data.source.UserRepository
import com.jonzarate.buymefood.data.source.UserSource
import com.google.firebase.firestore.FirebaseFirestoreSettings



class BuyMeFood : Application() {

    lateinit var userSource : UserSource

    lateinit var firestore : FirebaseFirestore
        private set

    lateinit var provider : BuyMeFoodViewModelProvider
        private set

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)
        // Required to handle timestamps due to Firestore change
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        firestore.firestoreSettings = settings

        firestore = FirebaseFirestore.getInstance()

        userSource = UserRepository(firestore)

        provider = BuyMeFoodViewModelProvider(userSource)
    }



}
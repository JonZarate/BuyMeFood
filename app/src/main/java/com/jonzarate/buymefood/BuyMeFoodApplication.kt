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

        setupFirebase()

        userSource = UserRepository(firestore)

        provider = BuyMeFoodViewModelProvider(userSource)
    }

    fun setupFirebase() {

        FirebaseApp.initializeApp(this)

        /* Due to forthcoming changes in Firebase, we need to setup how we handle dates */

        firestore = FirebaseFirestore.getInstance()
        val settings = FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build()
        firestore.firestoreSettings = settings
    }
}
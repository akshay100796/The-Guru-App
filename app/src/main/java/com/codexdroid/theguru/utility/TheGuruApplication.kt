package com.codexdroid.theguru.utility

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.HiltAndroidApp

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:07 PM
 * MH-15, India
 */
@HiltAndroidApp
class TheGuruApplication: Application() {

    private lateinit var crashlytics: FirebaseCrashlytics

    override fun onCreate() {
        super.onCreate()
        crashlytics = FirebaseCrashlytics.getInstance()
        FirebaseApp.initializeApp(applicationContext)
        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
    }
}
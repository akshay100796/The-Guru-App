package com.codexdroid.theguru.ui.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codexdroid.theguru.R
import com.codexdroid.theguru.ui.activities.login.LoginActivity
import com.codexdroid.theguru.ui.activities.home.HomeActivity
import com.codexdroid.theguru.utility.PrefManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Copyright (C) The-Guru - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 29 July 2023, 10:48 PM
 * MH-15, India
 */

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject lateinit var prefManager: PrefManager

    override fun onCreate(savedInstanceState: Bundle?) {
        val splash = installSplashScreen()
        super.onCreate(savedInstanceState)
        splash.setKeepOnScreenCondition { true }
        setContentView(R.layout.activity_splash)

        startActivity(Intent(this, prefManager.getToken()?.let { HomeActivity::class.java } ?: LoginActivity::class.java))
    }
}
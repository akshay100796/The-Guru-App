package com.codexdroid.theguru.ui.activities.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.codexdroid.theguru.databinding.ActivitySplashBinding
import com.codexdroid.theguru.di.room.TGViewModel
import com.codexdroid.theguru.di.room.tables.TableLearnings
import com.codexdroid.theguru.ui.activities.login.LoginActivity
import com.codexdroid.theguru.ui.activities.home.HomeActivity
import com.codexdroid.theguru.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private val tgViewModel by viewModels<TGViewModel>()


    override fun requestInitialised() {
        super.requestInitialised()

        CoroutineScope(Dispatchers.IO).launch {
            launch {  requestSaveLearnings() }
            launch {  requestLoadLearnings() }
        }
        when (Build.VERSION.SDK_INT) {

            //This is Min Version Android 10
            Build.VERSION_CODES.Q -> {
                Handler(Looper.getMainLooper()).postDelayed ({ toNext() }, 3000L)
            }
            Build.VERSION_CODES.R,Build.VERSION_CODES.S -> {
                installSplashScreen().setKeepOnScreenCondition { true }
                toNext()
            }
        }
    }

    private fun toNext() {
        startActivity(Intent(this, requestPreferenceManager().getToken()?.let { HomeActivity::class.java } ?: LoginActivity::class.java))
        finish()
    }

    private suspend fun requestSaveLearnings() {
        val learnings = listOf (
            TableLearnings(1,"Assurance in being","Guru Learnings"),
            TableLearnings(2,"Follow the path of heart","Guru Learnings"),
            TableLearnings(3,"Experience the power within","Kundalini Shakti")
        )
        tgViewModel.requestSaveLearnings(learnings)
    }

    private fun requestLoadLearnings() {
        requestViewModel().requestLoadLearnings(this)
    }
}
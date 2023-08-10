package com.codexdroid.theguru.ui.activities.create_event

import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.codexdroid.theguru.R
import com.codexdroid.theguru.databinding.ActivityCreateEventBinding
import com.codexdroid.theguru.ui.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 10 August 2023, 10:08 PM
 * MH-15, India
 */
@AndroidEntryPoint
class CreateEventActivity : BaseActivity<ActivityCreateEventBinding, CreateEventViewModel>() {

    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var navGraph: NavGraph

    private val onBackPress: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() { finish() }
    }

    override fun requestInitialised() {
        super.requestInitialised()

        onBackPressedDispatcher.addCallback(onBackPress)
        navHostFragment = supportFragmentManager.findFragmentById(R.id.id_nav_host_home_fragments) as NavHostFragment
        navController = navHostFragment.navController
        navGraph = navController.navInflater.inflate(R.navigation.nav_home)
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }



}
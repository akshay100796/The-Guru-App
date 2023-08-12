package com.codexdroid.theguru.ui.activities.home

import androidx.activity.OnBackPressedCallback
import androidx.navigation.NavController
import androidx.navigation.NavGraph
import androidx.navigation.fragment.NavHostFragment
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.HomeMenuAdapters
import com.codexdroid.theguru.controllers.data_models.local.HomeMenu
import com.codexdroid.theguru.controllers.interfaces.RecyclerItemClickListener
import com.codexdroid.theguru.databinding.ActivityHomeBinding
import com.codexdroid.theguru.ui.base.BaseActivity
import com.codexdroid.theguru.utility.AppConstants
import com.codexdroid.theguru.utility.requestDrawable
import dagger.hilt.android.AndroidEntryPoint

/**
 * Copyright (C) The-Guru - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:35 PM
 * MH-15, India
 */

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding, HomeActivityViewModel>() {

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
        requestSetUpHomeMenu()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }


    private fun requestSetUpHomeMenu() {
        val list = listOf<HomeMenu>(
            HomeMenu(getString(R.string.home), requestDrawable(resources,R.drawable.ic_black_filled_home), AppConstants.HomeMenus.MENU_HOME, true),
            HomeMenu(getString(R.string.events), requestDrawable(resources,R.drawable.ic_black_add_multi_circle),AppConstants.HomeMenus.MENU_EVENTS),
            HomeMenu(getString(R.string.past_events), requestDrawable(resources,R.drawable.ic_black_outline_history_time_repeat),AppConstants.HomeMenus.MENU_PAST_EVENTS),
            HomeMenu(getString(R.string.profile), requestDrawable(resources,R.drawable.ic_black_outline_person_circle),AppConstants.HomeMenus.MENU_PROFILE)
        )

        HomeMenuAdapters(this, list).apply {
            requestBinding().idRecyclerMenu.adapter = this
            requestOnRecyclerItemClicked(object : RecyclerItemClickListener{
                override fun onRecyclerItemClicked(position: Int, data: Any?, extra: String?) {
                    val menu = data as HomeMenu

                    list.map { it.isSelected = false }
                    list[position].isSelected = true
                    requestRefreshList(list)

                    when (menu.requestMenuType()) {
                        AppConstants.HomeMenus.MENU_HOME -> navigateTo(R.id.id_fragment_home)
                        AppConstants.HomeMenus.MENU_EVENTS -> navigateTo(R.id.id_fragment_events)
                        AppConstants.HomeMenus.MENU_PAST_EVENTS -> navigateTo(R.id.id_fragment_past_events)
                        AppConstants.HomeMenus.MENU_PROFILE -> navigateTo(R.id.id_fragment_profile)
                    }
                }
            })
        }
    }

    private fun navigateTo(toFragment: Int) {
        navController.popBackStack()
        navController.navigate(toFragment)
    }

}
package com.codexdroid.theguru.ui.activities.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.GuruLearningsAdapter
import com.codexdroid.theguru.controllers.data_models.local.Learnings
import com.codexdroid.theguru.databinding.ActivityLoginBinding
import com.codexdroid.theguru.ui.base.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 01 Aug 2023, 10:46 PM
 * MH-15, India
 */

class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    private val binding by lazy { requestBinding() }
    private val viewModel by lazy { requestViewModel() }

    private lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        letsSetUpListeners()
        letsSetUpObservers()
    }

    override fun onLoad() {
        super.onLoad()
        /**Load Local Data **/
        letsInitialized()
    }
    private fun letsInitialized() {
        setAdsAdapter()
    }


    private fun letsSetUpListeners() {}

    private fun letsSetUpObservers(){}

    private fun setAdsAdapter() {
        val learnings = listOf (
            Learnings("Assurance in being","Guru Learnings"),
            Learnings("Follow the path of heart","Guru Learnings"),
            Learnings("Experience the power within","Kundalini Shakti")
        )

        GuruLearningsAdapter(learnings).apply {
            binding.idLearningsViewpager.adapter = this
            timer = Timer()
            timer.scheduleAtFixedRate(object : TimerTask() {
                override fun run() {
                    CoroutineScope(Dispatchers.Main).launch {
                        val totalItems = binding.idLearningsViewpager.adapter?.count ?: 0
                        if(totalItems > 0) {
                            val next = (binding.idLearningsViewpager.currentItem + 1) % totalItems
                            binding.idLearningsViewpager.setCurrentItem(next,true)
                        }
                    }
                }
            },3000,3000)
        }
    }
}
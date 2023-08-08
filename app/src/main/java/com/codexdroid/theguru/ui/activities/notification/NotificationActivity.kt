package com.codexdroid.theguru.ui.activities.notification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.NotificationAdapter
import com.codexdroid.theguru.databinding.ActivityNotificationBinding
import com.codexdroid.theguru.ui.base.BaseActivity

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 09 August 2023, 12:27 AM
 * MH-15, India
 */

class NotificationActivity : BaseActivity<ActivityNotificationBinding, NotificationViewModel>() {

    override fun requestInitialised() {
        super.requestInitialised()
        requestShowNotifications()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
    }


    private fun requestShowNotifications() {
        NotificationAdapter().apply {
            requestBinding().idRecyclerNotification.adapter = this
        }
    }
}
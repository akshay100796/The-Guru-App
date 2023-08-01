package com.codexdroid.theguru.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:29 PM
 * MH-15, India
 */

class BaseFragmentViewModel(private val application:Application) : ViewModel() {}

@Suppress("UNCHECKED_CAST")
class BaseViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return BaseFragmentViewModel(application) as T
    }
}
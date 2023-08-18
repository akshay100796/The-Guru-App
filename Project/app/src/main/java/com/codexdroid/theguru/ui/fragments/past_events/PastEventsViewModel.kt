package com.codexdroid.theguru.ui.fragments.past_events

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 06 August 2023, 01:08 PM
 * MH-15, India
 */

class PastEventsViewModel : ViewModel() {

    private var _updateUi = MutableLiveData<String>()
    val updateUi : LiveData<String> = _updateUi

    fun requestUpdateUi(type: String) {
        _updateUi.postValue(type)
    }

}
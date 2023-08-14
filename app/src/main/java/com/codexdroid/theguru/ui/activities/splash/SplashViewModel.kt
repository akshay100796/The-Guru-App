package com.codexdroid.theguru.ui.activities.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codexdroid.theguru.di.network.repositories.NetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val networkRepository: NetworkRepository) : ViewModel() {

    fun requestLoadLearnings(context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            networkRepository.requestLoadLearnings(context)
        }
    }
}
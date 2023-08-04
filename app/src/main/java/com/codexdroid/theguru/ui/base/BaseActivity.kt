package com.codexdroid.theguru.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.codexdroid.theguru.di.room.TGViewModel
import com.codexdroid.theguru.utility.PrefManager
import com.google.firebase.auth.FirebaseAuth
import java.lang.reflect.ParameterizedType

/**
 * Copyright (C) ERxPharmaPro - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on ©  02 August 2023, 01:11 AM
 * MH-15, India
 */


abstract class BaseActivity<viewBinding: ViewBinding, viewModel: ViewModel>: AppCompatActivity() {

    private val type = (javaClass.genericSuperclass as ParameterizedType)
    @Suppress("UNCHECKED_CAST")
    private val viewBindingClass by lazy { type.actualTypeArguments[0] as Class<viewBinding> }
    @Suppress("UNCHECKED_CAST")
    private val viewModelClass by lazy { type.actualTypeArguments[1] as Class<viewModel> }
    private val inflateMethod = viewBindingClass.getMethod("inflate", LayoutInflater::class.java)

    private val _viewModel by lazy {  ViewModelProvider(this)[viewModelClass]  }
    @Suppress("UNCHECKED_CAST")
    private val _viewBinding by lazy { inflateMethod.invoke(null,layoutInflater) as viewBinding }

    private val _prefManager by lazy { PrefManager(this) }
    private val firebaseAuthentication by lazy { FirebaseAuth.getInstance() }
    private val _tgViewModel by viewModels<TGViewModel>()
    private val baseViewModel : BaseFragmentViewModel by viewModels { BaseViewModelFactory(application) }


    open fun requestBinding() = _viewBinding
    open fun requestViewModel() = _viewModel
    open fun requestPreferenceManager() = _prefManager
    open fun requestRoomViewModel() = _tgViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_viewBinding.root)
        requestInitialised()
        requestSetUpListeners()
        requestSetUpObserver()
        /**Load Data from local Storage here**/
    }

    open fun requestInitialised() {}
    open fun requestSetUpListeners() {}
    open fun requestSetUpObserver() {}

    open fun requestFirebaseAuth() = firebaseAuthentication

}
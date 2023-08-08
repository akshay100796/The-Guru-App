package com.codexdroid.theguru.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.codexdroid.theguru.di.room.TGViewModel
import com.codexdroid.theguru.di.room.TGViewModelFactory
import com.codexdroid.theguru.utility.PrefManager
import com.google.firebase.auth.FirebaseAuth
import java.lang.reflect.ParameterizedType

/**
 * Copyright (C) [The-Guru] - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Created by Akshay Pawar on © 31 July 2023, 11:28 PM
 * MH-15, India
 */

abstract class BaseFragment<viewBinding: ViewBinding, viewModel: ViewModel>: Fragment() {

    private val type = (javaClass.genericSuperclass as ParameterizedType)
    @Suppress("UNCHECKED_CAST")
    private val viewBindingClass by lazy { type.actualTypeArguments[0] as Class<viewBinding> }
    @Suppress("UNCHECKED_CAST")
    private val viewModelClass by lazy { type.actualTypeArguments[1] as Class<viewModel> }
    private val inflateMethod = viewBindingClass.getMethod("inflate", LayoutInflater::class.java)

    private val _viewModel by lazy {  ViewModelProvider(this)[viewModelClass]  }
    @Suppress("UNCHECKED_CAST")
    private val _viewBinding by lazy { inflateMethod.invoke(null,layoutInflater) as viewBinding }

    private val firebaseAuthentication by lazy { FirebaseAuth.getInstance() }
    private val roomViewModel : TGViewModel by viewModels { TGViewModelFactory(requireActivity().application) }
    private val prefManager by lazy { PrefManager(requireContext()) }
    private val fragmentViewModel : BaseFragmentViewModel by viewModels { BaseViewModelFactory(requireActivity().application) }

    open fun requestBinding() = _viewBinding
    open fun requestViewModel() = _viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        requestInitialised()
        requestSetUpListeners()
        requestSetUpObserver()

        return _viewBinding.root
    }


    open fun requestInitialised() {}
    open fun requestSetUpListeners() {}
    open fun requestSetUpObserver() {}

}
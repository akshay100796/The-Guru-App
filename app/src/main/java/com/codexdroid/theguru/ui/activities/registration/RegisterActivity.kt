package com.codexdroid.theguru.ui.activities.registration

import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.core.content.res.ResourcesCompat
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.GuruLearningsAdapter
import com.codexdroid.theguru.controllers.data_models.local.Learnings
import com.codexdroid.theguru.databinding.ActivityRegisterBinding
import com.codexdroid.theguru.di.room.tables.TableSelf
import com.codexdroid.theguru.ui.activities.home.HomeActivity
import com.codexdroid.theguru.ui.base.BaseActivity
import com.codexdroid.theguru.utility.AppConstants
import com.codexdroid.theguru.utility.isValidEmail
import com.codexdroid.theguru.utility.isValidMobile
import com.codexdroid.theguru.utility.requestGeneratePassword
import com.codexdroid.theguru.utility.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
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
 * Created by Akshay Pawar on © 31 July 2023, 11:43 PM
 * MH-15, India
 */

class RegisterActivity : BaseActivity<ActivityRegisterBinding, RegisterViewModel>() {

    private val binding by lazy { requestBinding() }
    private val viewModel by lazy { requestViewModel() }
    private val tgViewModel by lazy { requestRoomViewModel() }

    private lateinit var registerAs: String
    private lateinit var fullName: String
    private lateinit var email: String
    private lateinit var whatsApp: String
    private lateinit var password: String
    private var isPasswordAutoGenerated = false
    private lateinit var timer: Timer


    private val onBackPress: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

            MaterialAlertDialogBuilder(this@RegisterActivity)
                .setTitle(getString(R.string.confirm))
                .setMessage(getString(R.string.message_alert_cancel_registration))
                .setPositiveButton(getString(R.string.text_yes)) { _, _ -> finish() }
                .setNegativeButton(getString(R.string.text_no)){ dialog, _ -> dialog.dismiss() }
                .create().show()
        }
    }

    override fun requestInitialised() {
        super.requestInitialised()
        onBackPressedDispatcher.addCallback(onBackPress)
        requestShowLearningsAdapter()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()
        binding.idRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.id_chip_member -> {
                    viewModel.requestUpdateRegisterAs(Pair(getString(R.string.member), getString(R.string.member_select_desc)))
                }
                R.id.id_chip_admin -> {
                    viewModel.requestUpdateRegisterAs(Pair(getString(R.string.admin), getString(R.string.admin_select_desc)))
                }
                else -> {}
            }
        }
        binding.idButtonSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.requestUpdatePassword(if(isChecked) Pair(requestGeneratePassword(),true) else Pair("",false))
        }

        binding.idButtonRegister.setOnClickListener {
            fullName = binding.idEditFullName.text.toString()
            email = binding.idEditEmail.text.toString()
            whatsApp = binding.idEditWhatsApp.text.toString()
            password  = binding.idEditPassword.text.toString()

            val isInvalidName = fullName.isEmpty()
            viewModel.requestShowError(AppConstants.Errors.NAME, if(isInvalidName) getString(R.string.error_text_name_required) else "")
            if(isInvalidName) return@setOnClickListener

            val isInvalidEmail = !email.isValidEmail()
            viewModel.requestShowError(AppConstants.Errors.EMAIL, if(isInvalidEmail) getString(R.string.error_text_invalid_email) else "")
            if (isInvalidEmail) return@setOnClickListener

            val isInvalidWhatsApp = !whatsApp.isValidMobile()
            viewModel.requestShowError(AppConstants.Errors.WHATS_APP, if(isInvalidWhatsApp) getString(R.string.error_text_invaild_mobile) else "")
            if(isInvalidWhatsApp) return@setOnClickListener

            val isInvalidPassword = password.isEmpty() || password.length < 5
            viewModel.requestShowError(AppConstants.Errors.PASSWORD, if(isInvalidPassword) getString(R.string.error_text_invalid_password) else "")
            if(isInvalidPassword) return@setOnClickListener
            val tableSelf = TableSelf(registeredAs = registerAs, fullName = fullName, email = email, whatsApp = whatsApp, password = password, autoGenerated = isPasswordAutoGenerated)

            tgViewModel.requestCreateSelf(tableSelf)
            requestPreferenceManager().saveToken(AppConstants.Preferences.TEMP_TOKEN)
            startActivity(Intent(this@RegisterActivity, HomeActivity::class.java))
            showToast(this@RegisterActivity,"TODO: Approval of admin to get registered")
        }
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
        viewModel.registerAs.observe(this) {
            registerAs = it.first
            binding.idTextChipDesc.text = it.second
        }
        viewModel.updatePassword.observe(this) {
            isPasswordAutoGenerated = it.second
            binding.idEditPassword.apply {
                setText(it.first)
                isEnabled = !it.second
                background = ResourcesCompat.getDrawable(resources, if(it.second) R.drawable.drf_edit_disable else R.drawable.drf_edit_input_back, null)
            }
        }
    }

    private fun requestShowLearningsAdapter() {
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
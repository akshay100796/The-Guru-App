package com.codexdroid.theguru.ui.activities.login

import android.content.Intent
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import com.codexdroid.theguru.R
import com.codexdroid.theguru.controllers.adapters.GuruLearningsAdapter
import com.codexdroid.theguru.controllers.data_models.local.Learnings
import com.codexdroid.theguru.databinding.ActivityLoginBinding
import com.codexdroid.theguru.ui.activities.home.HomeActivity
import com.codexdroid.theguru.ui.activities.registration.RegisterActivity
import com.codexdroid.theguru.ui.base.BaseActivity
import com.codexdroid.theguru.utility.AppConstants
import com.codexdroid.theguru.utility.isValidEmail
import com.codexdroid.theguru.utility.isValidPassword
import com.codexdroid.theguru.utility.requestGeneratePassword
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
 * Created by Akshay Pawar on © 01 Aug 2023, 10:46 PM
 * MH-15, India
 */

/**
 * Links for Future Ref.
 * https://firebase.google.com/docs/auth/android/anonymous-auth
 * https://firebase.google.com/docs/firestore/quickstart
 * */
class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>() {

    private val binding by lazy { requestBinding() }
    private val viewModel by lazy { requestViewModel() }

    private lateinit var email: String
    private lateinit var password: String
    private lateinit var timer: Timer


    private val registerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    private val onBackPress: OnBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {

//            MaterialAlertDialogBuilder(this@HomeActivity)
//                .setTitle(getString(R.string.app_exit_title,getString(R.string.app_name)))
//                .setMessage(getString(R.string.app_exit_desc,getString(R.string.app_name)))
//                .setPositiveButton(getString(R.string.yes_pls)) { _, _ -> finishAffinity() }
//                .setNegativeButton(getString(R.string.no_i_don_t)){ dialog, _ -> dialog.dismiss() }
//                .create().show()
        }
    }

    override fun requestInitialised() {
        super.requestInitialised()
        /**Load Local Data **/
        onBackPressedDispatcher.addCallback(onBackPress)
        requestShowLearningsAdapter()
    }

    override fun requestSetUpListeners() {
        super.requestSetUpListeners()

        binding.idTextForgotPassword.setOnClickListener {

            email = binding.idEditEmail.text.toString()
            val isInvalid = email.isEmpty() || !email.isValidEmail()
            viewModel.requestShowError(AppConstants.Errors.EMAIL,if(isInvalid) getString(R.string.invalid_email_id) else "")
            if(isInvalid) return@setOnClickListener


            val password = requestGeneratePassword()
            showDialog()

            //TODO: TEMP Pass Set
            binding.idEditPassword.setText(password)
        }

        binding.idButtonLogin.setOnClickListener {
            email = binding.idEditEmail.text.toString()
            password = binding.idEditPassword.text.toString()

            val isInvalidEmail = email.isEmpty() || !email.isValidEmail()
            viewModel.requestShowError(AppConstants.Errors.EMAIL,if(isInvalidEmail) getString(R.string.invalid_email_id) else "")
            if(isInvalidEmail) return@setOnClickListener

            viewModel.requestShowError(AppConstants.Errors.PASSWORD, if(!password.isValidPassword()) getString(R.string.apologized_invalid_password) else "")
            if (!password.isValidPassword()) return@setOnClickListener

            /***
            Firebase.firestore.collection(AppConstants.Firestore.COLLECTION_LOGINS)
            .get()
            .addOnSuccessListener { result ->
            result.forEach {
            val map = it.data as HashMap<String, Any>
            val email = map[AppConstants.Firestore.LOGIN_ADMIN_EMAIL].toString()
            val password = map[AppConstants.Firestore.LOGIN_ADMIN_PASSWORD].toString()

            Log.d("AXE","Email: $email  ||  Password: $password")
            }
            }
             **/

            requestPreferenceManager().saveToken(AppConstants.Preferences.TEMP_TOKEN)
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
        }

        binding.idTextNewUser.setOnClickListener {
            Intent(this,RegisterActivity::class.java).apply {
                registerLauncher.launch(this)
            }
        }
    }

    override fun requestSetUpObserver() {
        super.requestSetUpObserver()
        viewModel.errorType.observe(this) {

            binding.idErrorTextEmail.text = it.second
            binding.idErrorTextEmail.visibility = if(it.first == AppConstants.Errors.EMAIL) View.VISIBLE else View.GONE

            binding.idErrorTextPassword.text = it.second
            binding.idErrorTextPassword.visibility = if(it.first == AppConstants.Errors.PASSWORD) View.VISIBLE else View.GONE
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

    private fun showDialog() {
        MaterialAlertDialogBuilder(this,R.style.selfDefineStyle)
            .setTitle(getString(R.string.forgot_password))
            .setCancelable(false)
            .setMessage(getString(R.string.forgot_pass_desc, email))
            .setPositiveButton(getString(R.string.yes_mail_me)){ dialog, _->
                dialog.dismiss()
            }
            .setNegativeButton(getString(R.string.no_cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
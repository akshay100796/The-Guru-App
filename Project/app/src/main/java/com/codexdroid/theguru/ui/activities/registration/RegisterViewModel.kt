package com.codexdroid.theguru.ui.activities.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private var _registerAs = MutableLiveData<Pair<String, String>>()
    val registerAs: LiveData<Pair<String, String>> = _registerAs

    private var _updatePassword = MutableLiveData<Pair<String, Boolean>>()
    val updatePassword: LiveData<Pair<String, Boolean>> = _updatePassword

    private var _showError = MutableLiveData<Pair<String, String>>()
    val showError : LiveData<Pair<String, String>> = _showError


    fun requestUpdateRegisterAs(registerAs: Pair<String, String>) {
        _registerAs.postValue(registerAs)
    }

    fun requestUpdatePassword(updatePassword: Pair<String, Boolean>) {
        _updatePassword.postValue(updatePassword)
    }

    fun requestShowError(errorType: String, text: String) {
        _showError.postValue(Pair(errorType, text))
    }

}
package com.codexdroid.theguru.di.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codexdroid.theguru.di.room.tables.TableSelf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TGViewModel @Inject constructor(private val tgRepository: TGRepository): ViewModel() {

    private var _selfInfo = MutableLiveData<TableSelf?> ()
    val selfInfo : LiveData<TableSelf?> = _selfInfo

    init {
        requestSelfInfo()
    }


    private fun requestSelfInfo() {
        viewModelScope.launch (Dispatchers.IO) {
            tgRepository.requestSelfInfo().collect {
                _selfInfo.postValue(it)
            }
        }
    }

    fun requestCreateSelf(tableSelf: TableSelf) {
        viewModelScope.launch (Dispatchers.IO) {
            tgRepository.requestCreateSelf(tableSelf)
        }
    }


}
package com.codexdroid.theguru.di.room

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.codexdroid.theguru.di.room.tables.TableLearnings
import com.codexdroid.theguru.di.room.tables.TableSelf
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TGViewModel @Inject constructor(private val application: Application): ViewModel() {

    val tgRepository by lazy { TGRepository(TGDatabase.requestDatabaseInstance(application.applicationContext).requestDaoInstance()) }

    private var _selfInfo = MutableLiveData<TableSelf?> ()
    val selfInfo : LiveData<TableSelf?> = _selfInfo

    private var _learnings = MutableLiveData<List<TableLearnings>> ()
    val learnings : LiveData<List<TableLearnings>> = _learnings


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

    suspend fun requestSaveLearnings(list: List<TableLearnings>) {
        viewModelScope.launch(Dispatchers.IO) {
            tgRepository.requestSaveLearnings(list)
        }
    }

    fun requestLoadLearnings() {
        viewModelScope.launch (Dispatchers.IO) {
            tgRepository.requestLoadLearnings().collect {
                _learnings.postValue(it)
            }
        }
    }

}

@Suppress("UNCHECKED_CAST")
class TGViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TGViewModel(application) as T
    }
}
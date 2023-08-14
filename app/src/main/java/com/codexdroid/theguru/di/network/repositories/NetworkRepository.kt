package com.codexdroid.theguru.di.network.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import com.codexdroid.theguru.di.network.NetworkResult
import com.codexdroid.theguru.di.network.services.RetrofitPublicService
import com.codexdroid.theguru.di.room.TGRepository
import com.codexdroid.theguru.di.room.tables.TableLearnings
import com.codexdroid.theguru.utility.NoConnectionInterceptor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val tgRepository: TGRepository,
    private val publicService: RetrofitPublicService) {

    private var _learningsResponse =  MutableLiveData<NetworkResult<List<TableLearnings>>?>(null)
    val learningsResponse : LiveData<NetworkResult<List<TableLearnings>>?> = _learningsResponse

    suspend fun requestLoadLearnings(context: Context) {

        if(NoConnectionInterceptor.isConnectionOn(context)) {

            _learningsResponse.postValue(NetworkResult.Loading())
            val response = publicService.requestLoadLearnings()
            if(response.isSuccessful) {
                response.body()?.let {
                    tgRepository.requestSaveLearnings(it)
                    _learningsResponse.postValue(NetworkResult.Success(it))
                }
            }else {
                loadFromCache(response.message())
            }
        } else {
            loadFromCache()
        }
    }

    private suspend fun loadFromCache(message: String? = null) {
        withContext(Dispatchers.IO){
            val learnings = async { tgRepository.requestLoadLearnings().asLiveData() }.await()
            if(learnings.value?.isNotEmpty() == true) {
                _learningsResponse.postValue(NetworkResult.Success(learnings.value!!))
            } else {
                _learningsResponse.postValue(NetworkResult.Error(message))
            }
        }
    }
}
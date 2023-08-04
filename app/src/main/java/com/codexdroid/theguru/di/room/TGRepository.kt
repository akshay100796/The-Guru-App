package com.codexdroid.theguru.di.room

import com.codexdroid.theguru.di.room.tables.TableSelf
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TGRepository @Inject constructor(private val tgDao: TGDao) {

    suspend fun requestCreateSelf(tableDoctorInfo: TableSelf) {
        tgDao.requestCreateSelf(tableDoctorInfo)
    }

    suspend fun requestSelfInfo() : Flow<TableSelf?> = flow {
        try {
            emit(tgDao.requestSelfInfo())
        }catch (ex: Exception) {
            emit(null)
        }
    }

}
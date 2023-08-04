package com.codexdroid.theguru.di.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.codexdroid.theguru.di.room.tables.TableSelf

@Dao
interface TGDao {


    //--------------------------------------------------------------------------CREATE
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun requestCreateSelf(tableDoctorInfo: TableSelf)


    //--------------------------------------------------------------------------READ
    @Transaction
    @Query("SELECT * FROM table_self")
    fun requestSelfInfo() : TableSelf



}
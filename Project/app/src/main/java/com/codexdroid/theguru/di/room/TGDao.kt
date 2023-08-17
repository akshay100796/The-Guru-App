package com.codexdroid.theguru.di.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.codexdroid.theguru.di.room.tables.TableLearnings
import com.codexdroid.theguru.di.room.tables.TableSelf

@Dao
interface TGDao {


    //--------------------------------------------------------------------------CREATE
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun requestCreateSelf(tableDoctorInfo: TableSelf)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun requestSaveLearnings(list: List<TableLearnings>)

    //--------------------------------------------------------------------------READ
    @Transaction
    @Query("SELECT * FROM table_self")
    fun requestSelfInfo() : TableSelf


    @Transaction
    @Query("SELECT * FROM table_learnings")
    fun requestLoadLearnings() : List<TableLearnings>



}
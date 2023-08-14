package com.codexdroid.theguru.di.room.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_learnings")
data class TableLearnings (
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val quote: String,
    val author: String,

)
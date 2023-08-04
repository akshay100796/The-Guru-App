package com.codexdroid.theguru.di.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.codexdroid.theguru.di.room.tables.TableSelf
import com.codexdroid.theguru.utility.AppConstants

@Database(entities = [TableSelf::class], version = 1)
abstract class TGDatabase : RoomDatabase() {
    abstract fun requestDaoInstance() : TGDao

    companion object {
        private var INSTANCE: TGDatabase? = null
        fun requestDatabaseInstance(context: Context): TGDatabase {
            synchronized(this) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        TGDatabase::class.java,
                        AppConstants.Preferences.DATABASE_NAME
                    ).setJournalMode(JournalMode.WRITE_AHEAD_LOGGING).build()
                }
            }
            return INSTANCE as TGDatabase
        }
    }
}
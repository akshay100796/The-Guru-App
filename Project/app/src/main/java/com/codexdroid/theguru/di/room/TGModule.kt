package com.codexdroid.theguru.di.room

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TGModule {

    @Singleton
    @Provides
    fun requestDatabaseInstance(@ApplicationContext context: Context) : TGDatabase = TGDatabase.requestDatabaseInstance(context)

    @Singleton
    @Provides
    fun requestDaoInstance(database:TGDatabase) : TGDao = database.requestDaoInstance()

}
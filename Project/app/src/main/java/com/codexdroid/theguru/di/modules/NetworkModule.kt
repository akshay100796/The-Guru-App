package com.codexdroid.theguru.di.modules

import android.content.Context
import com.codexdroid.theguru.di.network.repositories.NetworkRepository
import com.codexdroid.theguru.di.network.services.RetrofitPublicService
import com.codexdroid.theguru.di.room.TGRepository
import com.codexdroid.theguru.utility.AppConstants
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CipherSuite
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import okhttp3.TlsVersion
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context) : OkHttpClient.Builder {

        ConnectionSpec.Builder(ConnectionSpec.COMPATIBLE_TLS)
            .tlsVersions(TlsVersion.TLS_1_0)
            .cipherSuites(CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA)
            .build()

        val logInterceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }

        return OkHttpClient
            .Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            //.addInterceptor(NoConnectionInterceptor(context))
    }

    @Singleton
    @Provides
    fun provideGitUrl() : Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
            .baseUrl(AppConstants.LINKS.BASE_GIT_URL)
    }

    @Singleton
    @Provides
    fun provideRetrofitPublicService(builder: Retrofit.Builder, client: OkHttpClient.Builder) : RetrofitPublicService {
        return builder
            .client(client.build())
            .build()
            .create(RetrofitPublicService::class.java)
    }

    @Singleton
    @Provides
    fun provideNetworkRepository(tgRepository: TGRepository, retrofitPublicService: RetrofitPublicService) : NetworkRepository {
        return NetworkRepository(tgRepository, retrofitPublicService)
    }

}
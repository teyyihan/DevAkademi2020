package com.teyyihan.devakademi.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import com.teyyihan.devakademi.data.AdRepository
import com.teyyihan.devakademi.data.local.abstraction.LocalAdDataSource
import com.teyyihan.devakademi.data.local.impl.LocalAdDataSourceImpl
import com.teyyihan.devakademi.data.remote.DevAkademiAPI
import com.teyyihan.devakademi.data.remote.SpringAPI
import com.teyyihan.devakademi.data.remote.abstraction.RemoteAdDataSource
import com.teyyihan.devakademi.data.remote.impl.RemoteAdDataSourceImpl
import com.teyyihan.devakademi.db.MainDatabase
import com.teyyihan.devakademi.util.Consts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ApplicationModule {

    @Singleton
    @Provides
    fun provideRemoteAdDataSource(
        springAPI: SpringAPI,
        devAkademiAPI: DevAkademiAPI
    ): RemoteAdDataSource{
        return RemoteAdDataSourceImpl(springAPI, devAkademiAPI)
    }

    @Provides
    fun provideSpringAPI()
        = Retrofit.Builder()
        .baseUrl(Consts.SPRING_BASE_RUL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(SpringAPI::class.java)

    @Provides
    fun provideDevAkademiAPI()
        = Retrofit.Builder()
        .baseUrl(Consts.DEVAKADEMI_BASE_RUL)
        .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
        .build()
        .create(DevAkademiAPI::class.java)

    @Provides
    fun provideAdRepo(remoteAdDataSource: RemoteAdDataSource, mainDatabase: MainDatabase, localAdDataSource: LocalAdDataSource)
            = AdRepository(remoteAdDataSource, mainDatabase,localAdDataSource )


    @Provides
    fun provideLocalAdDataSource(mainDatabase: MainDatabase): LocalAdDataSource{
        return LocalAdDataSourceImpl(mainDatabase)
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): MainDatabase{
        return MainDatabase.getInstance(context)
    }


}
package com.example.desafioverity.data.di

import com.example.desafioverity.data.repository.UsersRepository
import com.example.desafioverity.data.repository.UsersRepositoryImpl
import com.example.desafioverity.data.service.Service
import com.example.desafioverity.domain.helpers.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesUserRepository(
        service: Service
    ): UsersRepository {
        return UsersRepositoryImpl(service)
    }

    @Provides
    fun providesRetrofit(): Service {
        val client = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                client
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }
}
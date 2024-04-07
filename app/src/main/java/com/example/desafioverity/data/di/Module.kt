package com.example.desafioverity.data.di

import com.example.desafioverity.data.repository.details.UserDetailRepository
import com.example.desafioverity.data.repository.details.UserDetailRepositoryImpl
import com.example.desafioverity.data.repository.repos.UserRepoRepository
import com.example.desafioverity.data.repository.repos.UserRepoRepositoryImpl
import com.example.desafioverity.data.repository.users.UserRepositoryImpl
import com.example.desafioverity.data.repository.users.UsersRepository
import com.example.desafioverity.data.service.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.github.com"
private const val BASE_URL2 = "http://demo7690382.mockable.io/"

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesUserRepository(
        service: Service
    ): UsersRepository {
        return UserRepositoryImpl(service)
    }

    @Provides
    fun provideUserDetailRepository(
        service: Service
    ): UserDetailRepository {
        return UserDetailRepositoryImpl(service)
    }

    @Provides
    fun provideRepoRepository(service: Service): UserRepoRepository {
        return UserRepoRepositoryImpl(service)
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
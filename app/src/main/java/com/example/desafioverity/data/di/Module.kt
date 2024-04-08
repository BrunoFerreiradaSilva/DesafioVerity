package com.example.desafioverity.data.di

import HeaderInterceptor
import com.example.desafioverity.data.repository.details.UserDetailRepository
import com.example.desafioverity.data.repository.details.UserDetailRepositoryImpl
import com.example.desafioverity.data.repository.preferences.UserPreferenceRepository
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

@InstallIn(SingletonComponent::class)
@Module
object Module {

    @Provides
    fun providesUserRepository(
        service: Service,
        userPreferencesRepository: UserPreferenceRepository
    ): UsersRepository {
        return UserRepositoryImpl(service, userPreferencesRepository)
    }

    @Provides
    fun provideUserDetailRepository(
        service: Service,
        userPreferencesRepository: UserPreferenceRepository
    ): UserDetailRepository {
        return UserDetailRepositoryImpl(service, userPreferencesRepository)
    }

    @Provides
    fun provideRepoRepository(
        service: Service, userPreferencesRepository: UserPreferenceRepository
    ): UserRepoRepository {
        return UserRepoRepositoryImpl(service, userPreferencesRepository)
    }

    @Provides
    fun providesRetrofit(userPreferencesRepository: UserPreferenceRepository): Service {
        val client = OkHttpClient.Builder()
            .readTimeout(1, TimeUnit.MINUTES)
            .addInterceptor(HeaderInterceptor(userPreferencesRepository))
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
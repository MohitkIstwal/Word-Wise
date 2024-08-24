package com.example.dictionaryapp.di
import com.example.dictionaryapp.data.api.WordApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    // this give a repo impl when asked for repo only
    @Singleton
    fun providesDictionaryApi() : WordApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(WordApi.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
            .create(WordApi::class.java)

    }
// this returns the wordApi instance and inject wherever it is required and hilt will use the same instance as the retrofit
}
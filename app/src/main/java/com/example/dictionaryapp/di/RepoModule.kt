package com.example.dictionaryapp.di

import com.example.dictionaryapp.data.repository.DictionaryRepositoryImpl
import com.example.dictionaryapp.domain.repository.DictionaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
// the module annotation helps the dagger hilt to provide dependency inject or helps the us to get instance of class in field constructor
@InstallIn(SingletonComponent::class)
// this helps the instance of class to accessible lifetime by app wherever required
abstract class RepositoryModule {
    @Binds
    // this annotation is required suppose if we want the dictionary repo containing data dagger hilt knows it give the repoimpl in response.
    @Singleton
    // this ensures that our app will contain only single instance of the dictionary repo.
    abstract fun bindDictionaryRepository(
        dictionaryRepositoryImpl: DictionaryRepositoryImpl
    ): DictionaryRepository
    // this above is used to give the instance of the repoimpl whenever the repo is called

}
// one instance of repo is created shared whenever required
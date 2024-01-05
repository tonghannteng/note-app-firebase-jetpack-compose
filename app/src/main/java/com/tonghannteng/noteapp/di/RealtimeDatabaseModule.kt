package com.tonghannteng.noteapp.di

import com.google.firebase.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * @author: Tonghann Teng
 * @since: 1/4/24
 */
@Module
@InstallIn(SingletonComponent::class)
class RealtimeDatabaseModule {

    @Provides
    @Singleton
    fun provideRealtimeDatabase(): Firebase {
        return Firebase
    }

}
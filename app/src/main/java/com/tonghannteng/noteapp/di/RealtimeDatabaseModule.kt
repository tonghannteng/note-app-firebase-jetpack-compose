package com.tonghannteng.noteapp.di

import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.tonghannteng.noteapp.data.remote.IRealtimeDatabase
import com.tonghannteng.noteapp.data.remote.RealtimeDatabaseImpl
import com.tonghannteng.noteapp.data.repository.IRepository
import com.tonghannteng.noteapp.data.repository.RepositoryImpl
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
    fun provideRealtimeDatabase(): DatabaseReference {
        return Firebase.database.getReference("note")
    }

    @Provides
    @Singleton
    fun injectRealtimeDatabase(databaseReference: DatabaseReference) =
        RealtimeDatabaseImpl(databaseReference = databaseReference) as IRealtimeDatabase

    @Provides
    @Singleton
    fun injectRepository(realtimeDatabase: IRealtimeDatabase) =
        RepositoryImpl(realtimeDatabase = realtimeDatabase) as IRepository

}

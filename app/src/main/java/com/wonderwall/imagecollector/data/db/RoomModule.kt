package com.wonderwall.imagecollector.data.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): ICDatabase {
        return Room.databaseBuilder(
            context,
            ICDatabase::class.java,
            "ic_database"
        ).build()
    }

    @Provides
    fun provideSearchedListDao(db: ICDatabase): SearchedListDao {
        return db.searchedListDao()
    }
}

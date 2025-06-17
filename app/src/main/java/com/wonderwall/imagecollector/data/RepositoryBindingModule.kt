package com.wonderwall.imagecollector.data

import com.wonderwall.imagecollector.data.db.SearchedListRepositoryImpl
import com.wonderwall.imagecollector.domain.repository.KakaoApiRepository
import com.wonderwall.imagecollector.domain.repository.SearchedListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryBindingModule {
    @Binds
    @Singleton
    abstract fun bindKakaoApiRepository(
        impl: KakaoApiRepositoryImpl
    ): KakaoApiRepository

    @Binds
    @Singleton
    abstract fun bindSearchedListRepositoryImpl(
        impl: SearchedListRepositoryImpl
    ): SearchedListRepository
}
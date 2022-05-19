package com.assignment.bongotalkies.di

import com.assignment.bongotalkies.network.MovieListService
import com.friendsapp.github.di.NetworkModule
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit


@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
abstract class TestNetworkModule {
    @Singleton
    @Binds
    abstract fun provideApiService(retrofit: Retrofit): MovieListService
}
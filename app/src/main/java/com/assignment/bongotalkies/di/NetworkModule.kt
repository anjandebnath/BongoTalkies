package com.friendsapp.github.di


import androidx.viewbinding.BuildConfig
import com.assignment.bongotalkies.network.MovieListService
import com.assignment.bongotalkies.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * A Hilt module is a class that is annotated with @Module
 * Sometimes a type cannot be constructor-injected.
 * For example, you cannot constructor-inject an interface.
 * You also cannot constructor-inject a type that you do not own,
 * such as a class from an external library.
 * In these cases, you can provide Hilt with binding information by using Hilt modules.
 */
@Module
/**
 * Unlike Dagger modules, you must annotate Hilt modules with @InstallIn
 * to tell Hilt which Android class each module will be used or installed in.
 */
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): MovieListService =
        retrofit.create(MovieListService::class.java)



}
package com.assignment.bongotalkies.movie_details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.assignment.bongotalkies.network.MovieListService
import com.assignment.bongotalkies.repository.MovieDetailsRepository
import com.google.gson.GsonBuilder
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import util.MockResponseFileReader

/**
 * Mock the Retrofit service to test the REST Api call
 */
@RunWith(JUnit4::class)
class MovieDetailsViewModelTest {


    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var movieDetailsRepository: MovieDetailsRepository
    private val server = MockWebServer()
    private lateinit var mockedResponse: String
    private val gson = GsonBuilder()
        .setLenient()
        .create()

    @Before
    fun setUp() {

        server.start(8000)
        var BASE_URL = server.url("/").toString()
        val okHttpClient = OkHttpClient
            .Builder()
            .build()
        val service = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .build().create(MovieListService::class.java)

        movieDetailsRepository = MovieDetailsRepository(service)
    }

    @Test
    fun testFetchMovieDetailsApi(){

        mockedResponse = MockResponseFileReader("DetailsResponseApi/success.json").content
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(mockedResponse)
        )
        val response = runBlocking { movieDetailsRepository.fetchMovieDetails(123) }
        val json = gson.toJson(response.body())
//        val resultResponse = JsonParser.parseString(json)
//        val expectedresponse = JsonParser.parseString(mockedResponse)
        Assert.assertNotNull(response)
        Assert.assertTrue(response.isSuccessful)



    }
}
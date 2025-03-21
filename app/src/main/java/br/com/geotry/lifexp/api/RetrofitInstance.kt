package br.com.geotry.lifexp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val weatherRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherService: OpenWeatherService = weatherRetrofit.create(OpenWeatherService::class.java)
}

package br.com.geotry.lifexp.model

data class WeatherResponse(
    val weather: List<Weather>,
    val main: Main
)

data class Weather(
    val main: String,
    val description: String
)

data class Main(
    val temp: Float,
    val humidity: Int
)

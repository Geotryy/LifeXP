package br.com.geotry.lifexp.api
import br.com.geotry.lifexp.model.WeatherResponse
import retrofit2.await

class WeatherRepository {
    suspend fun getWeather(city: String, apiKey: String): WeatherResponse? {
        return try {
            ApiClient.weatherService.getWeather(city, apiKey).await()
        } catch (e: Exception) {
            null
        }
    }
}

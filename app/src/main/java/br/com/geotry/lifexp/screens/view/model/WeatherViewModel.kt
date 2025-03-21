package br.com.geotry.lifexp.screens.view.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.geotry.lifexp.api.WeatherRepository
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel(){
    private val repository = WeatherRepository()

    var weatherMessage by mutableStateOf("Carregando previsão do tempo...")
        private set

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            val weather = repository.getWeather(city, apiKey)
            weather?.let {
                weatherMessage = getEnvironmentalTip(it.weather[0].main)
            } ?: run {
                weatherMessage = "Não foi possível obter a previsão do tempo."
            }
        }
    }

    private fun getEnvironmentalTip(weather: String): String {
        return when (weather) {
            "Clear" -> "☀️ Tempo ensolarado! Aproveite para economizar energia e usar luz natural."
            "Rain" -> "🌧️ Tempo chuvoso! Recolha água da chuva para regar suas plantas."
            "Clouds" -> "☁️ Tempo nublado! Bom momento para plantar mudas e evitar sol forte."
            else -> "🌍 Cuide do meio ambiente, independentemente do tempo!"
        }
    }

}
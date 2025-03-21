package br.com.geotry.lifexp.screens.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.geotry.lifexp.screens.view.model.WeatherViewModel

@Composable
fun WeatherInsightCard(weatherViewModel: WeatherViewModel) {

    LaunchedEffect(Unit) {
        weatherViewModel.fetchWeather("Sao Paulo", "d9190343673d9ae34fc834bf29559c8c")
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "🌍 Insight Ambiental de Hoje",
                fontSize = 18.sp,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            // 🔥 Exibe a mensagem dinâmica baseada no clima
            Text(
                text = weatherViewModel.weatherMessage,
                fontSize = 16.sp,
                color = Color.Gray
            )
        }
    }
}

package br.com.geotry.lifexp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.geotry.lifexp.R
import br.com.geotry.lifexp.screens.components.RowCustom
import br.com.geotry.lifexp.screens.view.model.GameViewModel

@Composable
fun TelaPremios(
    gameViewModel: GameViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF46D730),
                        Color(0xFF0D0D0D)
                    )
                )
            )
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Barra de informações de moedas
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Life XP Logo",
                modifier = Modifier.size(100.dp)
            )
            Text(
                modifier = Modifier.padding(vertical = 35.dp),
                text = "${gameViewModel.coins.value} COINS",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color =  Color.White
            )
        }
        Spacer(modifier = Modifier.height(60.dp))

        // Título da tela
        Text(
            text = "Prêmios",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFA500)
        )
        RowCustom()
        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Explore temas para seu avatar",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(40.dp))

        val premios = listOf(
            Pair(R.drawable.premio1, 50),
            Pair(R.drawable.premio2, 70),
            Pair(R.drawable.premio3, 100)
        )

        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(premios) { premio ->
                PremioItem(
                    preco = premio.second,
                    imageRes = premio.first,
                    gameViewModel = gameViewModel
                )
            }
        }
    }
}

@Composable
fun PremioItem(
    preco: Int,
    imageRes: Int,
    gameViewModel: GameViewModel
) {
    val comprado by remember { derivedStateOf { gameViewModel.wallpaper.value == imageRes } }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Wallpaper",
            modifier = Modifier
                .width(140.dp)
                .height(220.dp),
            contentScale = ContentScale.Crop
        )
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {
                    if (!comprado && gameViewModel.selecionarWallpaper(imageRes, preco)) {
                        // Atualização automática devido ao derivedStateOf
                    }
                },
                enabled = !comprado && gameViewModel.coins.value >= preco,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (comprado) Color.Gray else Color(0xFFFFA500)
                )
            ) {
                Text(
                    text = if (comprado) "Comprado" else "$preco Coins",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

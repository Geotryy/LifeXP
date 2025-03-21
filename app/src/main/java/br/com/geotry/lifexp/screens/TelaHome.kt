package br.com.geotry.lifexp.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.geotry.lifexp.R
import br.com.geotry.lifexp.screens.components.WeatherInsightCard
import br.com.geotry.lifexp.screens.view.model.GameViewModel
import br.com.geotry.lifexp.screens.view.model.WeatherViewModel

@Composable
fun TelaHome(
    navController: NavController,
    gameViewModel: GameViewModel,
    selectedCharacter: Int,
    nickname: String
) {
    val xp by gameViewModel.xp
    val nivel by gameViewModel.nivel
    val wallpaper by gameViewModel.wallpaper

    val weatherViewModel: WeatherViewModel = viewModel()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(listOf(Color(0xFF46D730), Color(0xFF0D0D0D)))),
        contentAlignment = Alignment.Center
    ) {
        // Se um wallpaper foi comprado, exibe a imagem
        wallpaper?.let {
            Image(
                painter = painterResource(id = it),
                contentDescription = "Fundo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                NavigateButton(
                    route = "telamissoes",
                    image = R.drawable.mapa,
                    name = "Missões",
                    description = "Imagem de um mapa",
                    navController = navController
                )
                NavigateButton(
                    navController = navController,
                    image = R.drawable.premio,
                    name = "Prêmios",
                    description = "Botão de Prêmios",
                    route = "telapremios"
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            Column(
                modifier = Modifier.padding(horizontal = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Barra de XP e Nível
                XpBar(nivel, xp)
                Spacer(modifier = Modifier.height(30.dp))
                AnimatedCharacter(selectedCharacter)
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = nickname.uppercase(),
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .background(color = Color(0xFFFF884D), shape = RoundedCornerShape(20.dp))
                        .height(5.dp)
                )
                Spacer(Modifier.height(20.dp))
                WeatherInsightCard(weatherViewModel = weatherViewModel)
            }

            Spacer(modifier = Modifier.weight(1f))
            Logo()
        }
    }
}

// Botão de navegação (Missões e Prêmios)
@Composable
fun NavigateButton(
    navController: NavController,
    image: Int,
    name: String,
    description: String,
    route: String
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = image),
            contentDescription = description,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .size(55.dp)
                .clickable { navController.navigate(route) }
        )
        Text(
            text = name,
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// Barra de XP e Nível
@Composable
fun XpBar(nivel: Int, xp: Int) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Lvl $nivel | ${xp} XP",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        LinearProgressIndicator(
            progress = { xp / 100f },
            color = Color(0xFFFF884D),
            trackColor = Color.White,
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(8.dp)
                .clip(RoundedCornerShape(16.dp))
        )
    }
}

// Animação do personagem
@Composable
fun AnimatedCharacter(selectedCharacter: Int) {
    val positionY by rememberInfiniteTransition().animateFloat(
        initialValue = 0f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Image(
        painter = painterResource(
            id = if (selectedCharacter == 0) R.drawable.girl else R.drawable.boy
        ),
        contentDescription = "Personagem Selecionado",
        modifier = Modifier
            .size(300.dp)
            .offset(y = positionY.dp)
    )
}

// Logo do App
@Composable
fun Logo() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 5.dp, bottom = 16.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo LIFE XP",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(100.dp)
        )
    }
}

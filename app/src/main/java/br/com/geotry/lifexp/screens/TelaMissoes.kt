package br.com.geotry.lifexp.screens

import android.inputmethodservice.Keyboard.Row
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.geotry.lifexp.R
import br.com.geotry.lifexp.screens.components.RowCustom
import br.com.geotry.lifexp.screens.view.model.GameViewModel

@Composable
fun TelaMissoes(navController: NavController, gameViewModel: GameViewModel) {
    val xp by gameViewModel.xp
    val coins by gameViewModel.coins
    val nivel by gameViewModel.nivel

    val missoesFaceis = listOf(
        "Desligue luzes desnecessárias" to Pair(30, 10),
        "Recicle corretamente seu lixo" to Pair(30, 10),
        "Evite usar sacolas plásticas ao fazer compras" to Pair(30, 10),
        "Use uma garrafa reutilizável ao invés de descartável" to Pair(30, 10),
        "Reduza seu banho para menos de 5 minutos" to Pair(30, 10)
    )

    val missoesDificeis = listOf(
        "Plante uma árvore ou cultive uma horta" to Pair(70, 50),
        "Fique um dia inteiro sem usar plásticos descartáveis" to Pair(70, 50),
        "Participe de um mutirão de limpeza na sua cidade" to Pair(70, 50),
        "Adote um estilo de vida sustentável por uma semana" to Pair(70, 50),
        "Doe roupas ou objetos que você não usa mais" to Pair(70, 50)
    )

    val missoesConcluidas = remember { mutableStateMapOf<String, Boolean>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF46D730), Color(0xFF0D0D0D))
                )
            )
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
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
                modifier = Modifier.padding(vertical = 40.dp),
                text = "$xp XP | $coins COINS",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "🌎 Missões Ambientais 🌱",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFD700)
        )

        RowCustom()

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = "Pequenas Ações \n(30 XP | 10 Coins)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(missoesFaceis) { missao ->
                val isChecked = missoesConcluidas[missao.first] == true

                MissaoItem(
                    text = missao.first,
                    xpGanho = missao.second.first,
                    coinsGanho = missao.second.second,
                    isChecked = isChecked,
                    onCheckedChange = { checked ->
                        missoesConcluidas[missao.first] = checked
                        if (checked) {
                            gameViewModel.adicionarXP(missao.second.first)
                            gameViewModel.adicionarCoins(missao.second.second)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Grandes Impactos \n(70 XP | 50 Coins)",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFFA500)
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(missoesDificeis) { missao ->
                val isChecked = missoesConcluidas[missao.first] == true

                MissaoItem(
                    text = missao.first,
                    xpGanho = missao.second.first,
                    coinsGanho = missao.second.second,
                    isChecked = isChecked,
                    onCheckedChange = { checked ->
                        missoesConcluidas[missao.first] = checked
                        if (checked) {
                            gameViewModel.adicionarXP(missao.second.first)
                            gameViewModel.adicionarCoins(missao.second.second)
                        }
                    }
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun MissaoItem(
    text: String,
    xpGanho: Int,
    coinsGanho: Int,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    val transition = updateTransition(targetState = isChecked, label = "CheckTransition")
    val backgroundColor by transition.animateColor(label = "BackgroundColor") { checked ->
        if (checked) Color(0xFF4CAF50).copy(alpha = 0.2f) else Color(0x80FFFFFF)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable { onCheckedChange(!isChecked) }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "+$xpGanho XP | +$coinsGanho Coins",
                color = Color(0xFFD4AF37),
                fontSize = 14.sp
            )
        }

        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            colors = CheckboxDefaults.colors(
                checkedColor = Color(0xFF4CAF50),
                uncheckedColor = Color.White,
                checkmarkColor = Color.White
            )
        )
    }
}

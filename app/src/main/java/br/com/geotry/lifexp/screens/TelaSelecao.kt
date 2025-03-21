package br.com.geotry.lifexp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
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
import br.com.geotry.lifexp.screens.components.ButtonCustom
import br.com.geotry.lifexp.screens.view.model.TelaSelecaoViewModel

@Composable
fun TelaSelecao(
    navController: NavController,
    telaSelecaoViewModel: TelaSelecaoViewModel
) {
    val selectedCharacter by telaSelecaoViewModel.selectedCharacterState.observeAsState(initial = 0)
    val nickname by telaSelecaoViewModel.nicknameState.observeAsState(initial = "")
    val nicknameError by telaSelecaoViewModel.nicknameErrorState.observeAsState(initial = false)


    var tamanhoNickname = 8;

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    listOf(
                        Color(0xFF46D730),
                        Color(0xFF0D0D0D)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.padding(30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo do LIFEXP",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(150.dp)
            )
            Text(
                text = "Escolha Seu Personagem",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(30.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.girl),
                        contentDescription = "Garota Aventureira",
                        modifier = Modifier.size(150.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {

                        RadioButton(
                            selected = selectedCharacter == 0,
                            onClick = { telaSelecaoViewModel.setSelectedCharacter(0) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.White,
                                unselectedColor = Color.White
                            )

                        )
                        Text(text = "Aventureira", color = Color.White)
                    }

                }
                Column {
                    Image(
                        painter = painterResource(id = R.drawable.boy),
                        contentDescription = "Garoto Aventureiro",
                        modifier = Modifier.size(150.dp)
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        RadioButton(
                            selected = selectedCharacter == 1,
                            onClick = { telaSelecaoViewModel.setSelectedCharacter(1) },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = Color.White,
                                unselectedColor = Color.White
                            )
                        )
                        Text(text = "Aventureiro", color = Color.White)
                    }
                }


            }
            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                placeholder = { Text(text = "Insira seu nickname", color = Color.White) },
                value = nickname,
                isError = nicknameError,
                onValueChange = {
                    if (it.length <= tamanhoNickname) telaSelecaoViewModel.setNickname(
                        it
                    )
                },
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Color(0x26F8F8F8),
                    focusedContainerColor = Color(0x26F8F8F8),
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Color.Transparent

                )
            )
            Spacer(modifier = Modifier.height(40.dp))

            ButtonCustom(
                text = "Vamos!",
                onClick = {
                        navController.navigate("telahome/$selectedCharacter/$nickname/0/1")
                }
            )

        }
    }
}

package br.com.geotry.lifexp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.geotry.lifexp.screens.*
import br.com.geotry.lifexp.screens.view.model.GameViewModel
import br.com.geotry.lifexp.screens.view.model.TelaSelecaoViewModel
import br.com.geotry.lifexp.ui.theme.LIFEXPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val gameViewModel: GameViewModel = viewModel()

            NavHost(
                navController = navController,
                startDestination = "telainicial"
            ) {
                composable(route = "telainicial") {
                    TelaInicial(navController = navController)
                }
                composable(route = "telaselecao") {
                    TelaSelecao(
                        navController = navController,
                        telaSelecaoViewModel = TelaSelecaoViewModel()
                    )
                }
                composable("telahome/{selectedCharacter}/{nickname}/{xp}/{nivel}") { backStackEntry ->
                    val selectedCharacter =
                        backStackEntry.arguments?.getString("selectedCharacter")?.toIntOrNull() ?: 0
                    val nickname = backStackEntry.arguments?.getString("nickname") ?: ""
                    val xp = backStackEntry.arguments?.getString("xp")?.toFloatOrNull() ?: 0f
                    val nivel = backStackEntry.arguments?.getString("nivel")?.toIntOrNull() ?: 1

                    TelaHome(
                        navController = navController,
                        selectedCharacter = selectedCharacter,
                        nickname = nickname,
                        gameViewModel = gameViewModel,
                    )
                }
                composable(route = "telamissoes") {
                    TelaMissoes(
                        navController = navController,
                        gameViewModel = gameViewModel
                    )
                }
                composable(route = "telapremios") {
                    TelaPremios(
                        gameViewModel = gameViewModel
                    )
                }
            }
        }}}
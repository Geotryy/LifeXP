package br.com.geotry.lifexp.screens.view.model

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf

class GameViewModel : ViewModel() {
    var xp = mutableStateOf(0)
    var nivel = mutableStateOf(1)
    var coins = mutableStateOf(150)  // Começa com 150 coins
    var wallpaper = mutableStateOf<Int?>(null) // Nenhum wallpaper selecionado

    fun adicionarXP(valor: Int) {
        xp.value += valor
        while (xp.value >= 100) {
            xp.value -= 100  // Em vez de resetar para 0, remove apenas 100
            nivel.value += 1
        }
    }


    fun adicionarCoins(valor: Int) {
        coins.value += valor
    }

    fun gastarCoins(valor: Int): Boolean {
        return if (coins.value >= valor) {
            coins.value -= valor
            true
        } else {
            false
        }
    }

    fun selecionarWallpaper(novoWallpaper: Int, custo: Int): Boolean {
        return if (gastarCoins(custo)) {
            wallpaper.value = novoWallpaper
            true
        } else {
            false
        }
    }}

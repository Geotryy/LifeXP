package br.com.geotry.lifexp.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RowCustom(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.7f)
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
            .height(3.dp)
    )
}
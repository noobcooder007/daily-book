package com.bonsaisoftware.dailybook.ui.components

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FAB(modifier: Modifier = Modifier, onClick: () -> Unit = {}, icon: @Composable () -> Unit = {}) {
    FloatingActionButton(
        shape = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp),
        onClick = { onClick() },
        modifier = modifier,
    ) {
        icon()
    }
}
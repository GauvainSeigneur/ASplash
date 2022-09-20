package com.gauvain.seigneur.design

import androidx.compose.runtime.Composable

@Composable
fun ThemedContent(content: @Composable () -> Unit) {
    ASplashTheme {
        content()
    }
}
package com.gauvain.seigneur.design.pagination

import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun ASplashSwipeRefresh(
    isListRefreshing: Boolean,
    onRefreshAction: () -> Unit,
    content: @Composable () -> Unit,
) {
    val swipeRefreshState = rememberSwipeRefreshState(isListRefreshing)
    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = {
            onRefreshAction()
        }
    ) {
        content()
    }
}
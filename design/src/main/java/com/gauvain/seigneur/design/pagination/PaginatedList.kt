package com.gauvain.seigneur.design.pagination

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items

@Composable
fun <T : Any> PaginatedList(
    lazyPagingItems: LazyPagingItems<T>,
    item: @Composable (T?) -> Unit,
    loadingItem: @Composable () -> Unit,
    failLoadItem: @Composable (LoadState.Error) -> Unit,
) {
    var isListRefreshing by remember { mutableStateOf(false) }

    ASplashSwipeRefresh(
        isListRefreshing = isListRefreshing,
        onRefreshAction = {
            isListRefreshing = true
            lazyPagingItems.refresh()
        },
        content = {
            // handle first load
            when (lazyPagingItems.loadState.refresh) {
                is LoadState.NotLoading -> {
                    //todo - remove big first view
                    Log.d("PaginatedList", "refresh a")
                }
                is LoadState.Loading -> {
                    if (lazyPagingItems.itemCount > 0) {
                        // todo - here we refresh an existing list, so we do not need to display the big view
                        Log.d("PaginatedList", "refresh b 1")
                    } else {
                        // todo - display first big view
                        Log.d("PaginatedList", "refresh b 2")
                    }
                }
                is LoadState.Error -> {
                    // todo - display first big view
                    Log.d("PaginatedList", "refresh c")
                }
            }

            // display list
            LazyColumn {
                // populate when new items are added
                items(lazyPagingItems) { photo ->
                    // when item are append refresh is done
                    isListRefreshing = false
                    item(photo)
                }

                // handle next loads
                when (lazyPagingItems.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item {
                            loadingItem()
                        }
                    }
                    is LoadState.Error -> {
                        Log.d("PaginatedList", "next load error")
                        item {
                            failLoadItem(lazyPagingItems.loadState.append as LoadState.Error)
                        }
                    }
                }
            }
        }
    )
}

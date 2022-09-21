package com.gauvain.seigneur.asplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.gauvain.seigneur.design.ThemedContent
import com.gauvain.seigneur.domain.model.Photo
import com.gauvain.seigneur.domain.usecase.GetMeUseCase
import com.gauvain.seigneur.domain.usecase.GetPhotosUseCase
import com.gauvain.seigneur.domain.usecase.GetUserUseCase
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var getUserUseCase: GetUserUseCase

    @Inject
    lateinit var getPhotosUseCase: GetPhotosUseCase

    @Inject
    lateinit var getMeUseCase: GetMeUseCase

    private val scope = CoroutineScope(Dispatchers.IO)
    private var iSelected = false

    private val viewModel by viewModels<MainViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         
        setContent {
            val photosData = viewModel.photoPager.collectAsLazyPagingItems()
            ThemedContent {
                PaginatedList(
                    lazyPagingItems = photosData,
                    item = { photo ->
                        PhotoItem(photo)
                    },
                    loadingItem = {
                        Card(modifier = Modifier.padding(8.dp)) {
                            Text(text = "loading item")
                        }
                    },
                    failLoadItem = { loadStateError ->
                        Card(modifier = Modifier.padding(8.dp)) {
                            Text(text = loadStateError.error.message.toString())
                        }
                    }
                )

                /* SwipeRefresh(
                     state = swipeRefreshState,
                     onRefresh = {
                         refreshing = true
                         photosData.refresh()
                     },
                 ) {
                     LazyColumn {
                         items(photosData) { photo ->
                             // when item are append refresh is done
                             refreshing = false
                             Card(modifier = Modifier.padding(8.dp)) {
                                 Text(text = photo?.id ?: "noId")
                             }
                         }
                         when (photosData.loadState.append) {
                             is LoadState.NotLoading -> Unit
                             LoadState.Loading -> {
                                 if (photosData.itemCount < 0) {
                                     Log.d("blablbla", "first load")
                                 }
                                 item {
                                     Card(modifier = Modifier.padding(8.dp)) {
                                         Text(text = "loading item")
                                     }
                                 }
                             }
                             is LoadState.Error -> {
                                 item {
                                     Card(modifier = Modifier.padding(8.dp)) {
                                         Text(text = (photosData.loadState.append as LoadState.Error).error.message.toString())
                                     }
                                 }
                             }
                         }

                     }
                 }*/

                /*Column {
                    Text(text = "Text example")
                    Button(onClick = { }) {
                        Text(text = "Button")
                    }
                    Card(modifier = Modifier.padding(8.dp)) {
                        Text(text = "CardContent")
                    }
                    FilterChip(selected = iSelected, onClick = { !iSelected }, label = {
                        Text(text = "filter chip")
                    })
                    ElevatedFilterChip(selected = iSelected, onClick = { !iSelected}, label = {
                        Text(text = "Elevated Filter Chip")
                    })
                    AssistChip(onClick = { }, label = {
                        Text(text = "assist chip")
                    })
                    InputChip(selected = iSelected, onClick = { !iSelected }, label =   { Text(text = "InputChip")})
                    ElevatedSuggestionChip(onClick = {}, label =   { Text(text = "ElevatedSuggestionChip")})

                    }*/

            }

        }
    }

    @Composable
    fun <T : Any> PaginatedList(
        lazyPagingItems: LazyPagingItems<T>,
        item: @Composable (T?) -> Unit,
        loadingItem: @Composable () -> Unit,
        failLoadItem: @Composable (LoadState.Error) -> Unit,
    ) {
        var refreshing by remember { mutableStateOf(false) }
        val swipeRefreshState = rememberSwipeRefreshState(refreshing)

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = {
                refreshing = true
                lazyPagingItems.refresh()
            },
        ) {
            // handle first load
            when (lazyPagingItems.loadState.refresh) {
                is LoadState.NotLoading -> {
                    //todo - remove big first view
                    Log.d("weshalors", "refresh a")
                }
                is LoadState.Loading -> {
                    if(lazyPagingItems.itemCount > 0) {
                        // todo - here we refresh an existing list, so we do not need to display the big view
                        Log.d("weshalors", "refresh b 1")
                    } else {
                        // todo - display first big view
                        Log.d("weshalors", "refresh b 2")
                    }
                }
                is LoadState.Error -> {
                    // todo - display first big view
                    Log.d("weshalors", "refresh c")
                }
            }

            LazyColumn {
                // populate when new items are added
                items(lazyPagingItems) { photo ->
                    // when item are append refresh is done
                    refreshing = false
                    item(photo)
                }
                Log.d("weshalors", "lol ${lazyPagingItems.loadState}")
                // handle next loads
                when (lazyPagingItems.loadState.append) {
                    is LoadState.NotLoading -> Unit
                    LoadState.Loading -> {
                        item {
                            loadingItem()
                        }
                    }
                    is LoadState.Error -> {
                        Log.d("lolilol", "lol error")
                        item {
                            failLoadItem(lazyPagingItems.loadState.append as LoadState.Error)
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun PhotoItem(photo: Photo?) {
        Card(modifier = Modifier.padding(8.dp)) {
            Text(text = photo?.id ?: "noId")
        }
    }
}


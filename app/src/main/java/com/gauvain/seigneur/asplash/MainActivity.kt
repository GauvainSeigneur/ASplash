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
import com.gauvain.seigneur.design.pagination.PaginatedList
import com.gauvain.seigneur.design.theme.ThemedContent
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
    fun PhotoItem(photo: Photo?) {
        Card(modifier = Modifier.padding(8.dp)) {
            Text(text = photo?.id ?: "noId")
        }
    }
}


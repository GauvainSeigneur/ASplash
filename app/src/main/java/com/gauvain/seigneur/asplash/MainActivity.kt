package com.gauvain.seigneur.asplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gauvain.seigneur.data.repository.UserDataRepository
import com.gauvain.seigneur.design.ThemedContent
import com.gauvain.seigneur.domain.usecase.GetMeUseCase
import com.gauvain.seigneur.domain.usecase.GetPhotosUseCase
import com.gauvain.seigneur.domain.usecase.GetUserUseCase
import com.google.android.material.chip.ChipGroup
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

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            ThemedContent {
                Column {
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

                    }
                }



            }
        }

    }

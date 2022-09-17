package com.gauvain.seigneur.asplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gauvain.seigneur.data.repository.UserDataRepository
import com.gauvain.seigneur.domain.usecase.GetMeUseCase
import com.gauvain.seigneur.domain.usecase.GetPhotosUseCase
import com.gauvain.seigneur.domain.usecase.GetUserUseCase
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scope.launch {
            getPhotosUseCase()
            //getUserUseCase("gauvains")
            getMeUseCase()
        }

    }
}
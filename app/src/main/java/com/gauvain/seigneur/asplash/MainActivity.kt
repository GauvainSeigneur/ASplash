package com.gauvain.seigneur.asplash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.gauvain.seigneur.data.repository.GetUserRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repo: GetUserRepository

    private val scope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scope.launch {
            repo.getMe()
        }

    }
}
package com.gauvain.seigneur.asplash

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ASplashApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
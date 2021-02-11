package com.arjun1194.nativenews

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // We are what we choose to be , this is the beginning
    }
}
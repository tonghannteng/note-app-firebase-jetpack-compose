package com.tonghannteng.noteapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @author: Tonghann Teng
 * @since: 1/4/24
 */
@HiltAndroidApp
class App : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
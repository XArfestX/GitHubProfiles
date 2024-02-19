package com.arfest.githubprofiles.app

import android.app.Application
import com.arfest.githubprofiles.di.DaggerAppComponent
import com.arfest.githubprofiles.di.DiProvider

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDagger()
    }

    private fun setupDagger() {
        val appComponent = DaggerAppComponent
            .builder()
            .addContext(this)
            .build()
        DiProvider.appComponent = appComponent
    }
}
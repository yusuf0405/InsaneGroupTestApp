package com.joseph.insanegrouptestapp

import android.app.Application
import com.joseph.insanegrouptestapp.di.appModule
import com.joseph.insanegrouptestapp.di.dataModule
import com.joseph.insanegrouptestapp.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule + dataModule + domainModule)
        }
    }
}
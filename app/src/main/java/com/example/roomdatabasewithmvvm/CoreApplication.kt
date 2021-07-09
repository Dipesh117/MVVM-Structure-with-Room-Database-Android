package com.example.roomdatabasewithmvvm

import android.app.Application
import com.example.roomdatabasewithmvvm.di.dbModule
import com.example.roomdatabasewithmvvm.di.repositoryModule
import com.example.roomdatabasewithmvvm.di.uiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApplication)
            modules(listOf(dbModule, repositoryModule,  uiModule))
        }
    }
}
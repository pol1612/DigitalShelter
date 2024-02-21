package com.pol.sane.jove.digitalshelter

import android.app.Application
import com.pol.sane.jove.digitalshelter.model.service.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DigitalShelterApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DigitalShelterApplication)
            modules(appModule)
        }
    }
}
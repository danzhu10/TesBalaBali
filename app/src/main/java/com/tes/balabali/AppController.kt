package com.tes.balabali

import android.app.Application
import com.tes.balabali.presentation.di.presentationModule
import com.tes.balabali.data.di.dataModule
import com.tes.balabali.domain.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

lateinit var appContext: Application

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@AppController)
            modules(
                dataModule,
                remoteModule,
                domainModule,
                presentationModule
            )
        }
    }

    companion object {
        lateinit var instance: AppController
    }

}
package io.arkstud.wordbox_roberto

import android.app.Application
import android.content.IntentFilter
import android.util.Log
import io.arkstud.wordbox_roberto.di.networkServicesModule
import io.arkstud.wordbox_roberto.di.repositoryModule
import io.arkstud.wordbox_roberto.di.utilModule
import io.arkstud.wordbox_roberto.di.viewModelModule
import io.arkstud.wordbox_roberto.model.receiver.ConnectivityReceiver
import org.koin.android.ext.android.startKoin

class WordboxRobertoApplication : Application() {

    /**
     *
     *
     */
    override fun onCreate() {
        super.onCreate()
        initializeKoin()
        initializeBroadcast()
    }

    /**
     *
     *
     */
    private fun initializeKoin(){
        val modules = listOf(viewModelModule, repositoryModule, networkServicesModule, utilModule)
        startKoin(this, modules)
    }

    /**
     *
     *
     */
    private fun initializeBroadcast(){
        registerReceiver(ConnectivityReceiver(), IntentFilter(BuildConfig.CONNECTIVITY_CHANGE))
    }

}
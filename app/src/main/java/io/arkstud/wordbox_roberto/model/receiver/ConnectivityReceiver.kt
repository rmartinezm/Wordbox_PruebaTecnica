package io.arkstud.wordbox_roberto.model.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import io.arkstud.wordbox_roberto.model.repository.internet_connection.InternetConnectionRepository
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ConnectivityReceiver : BroadcastReceiver(), KoinComponent {

    private val internetConnectionRepository: InternetConnectionRepository by inject()

    override fun onReceive(context: Context?, intent: Intent?) {
        internetConnectionRepository.fetchInternetConnectionValue()
    }

}
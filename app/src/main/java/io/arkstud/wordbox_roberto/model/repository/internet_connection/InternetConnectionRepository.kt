package io.arkstud.wordbox_roberto.model.repository.internet_connection

import androidx.lifecycle.LiveData

interface InternetConnectionRepository {

    /* LiveData that indies if the internet connection is available */
    val internetConnectionAvailable: LiveData<Boolean>

    /**
     *
     *
     */
    fun fetchInternetConnectionValue()

}
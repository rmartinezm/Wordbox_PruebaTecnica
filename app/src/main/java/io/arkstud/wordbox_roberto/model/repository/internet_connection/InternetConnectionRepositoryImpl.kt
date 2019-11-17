package io.arkstud.wordbox_roberto.model.repository.internet_connection

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.runBlocking
import java.net.HttpURLConnection
import java.net.URL

class InternetConnectionRepositoryImpl : InternetConnectionRepository {

    private val _internetConnectionAvailable: MutableLiveData<Boolean> = MutableLiveData()
    override val internetConnectionAvailable: LiveData<Boolean> get() = _internetConnectionAvailable

    /**
     *
     */
    override fun fetchInternetConnectionValue() {
        val task = CheckInternetTask { _internetConnectionAvailable.postValue(it) }
        task.execute()
    }

    /**
     *
     * @param onComplete
     */
    private class CheckInternetTask(val onComplete: (value: Boolean) -> Unit) : AsyncTask<Void, Void, Boolean>() {

        /**
         *
         * @param params
         */
        override fun doInBackground(vararg params: Void): Boolean? = try {
            val url = URL("https://clients3.google.com/generate_204")
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            urlConnection.setRequestProperty("Person-Agent", "Android")
            urlConnection.setRequestProperty("Connection", "close")
            urlConnection.connectTimeout = 1500
            urlConnection.connect()
            urlConnection.responseCode == 204 && urlConnection.contentLength == 0
        } catch (ignore: Exception) { false }

        /**
         *
         * @param isInternetAvailable
         */
        override fun onPostExecute(isInternetAvailable: Boolean?) {
            onComplete(isInternetAvailable ?: false)
        }

    }

}
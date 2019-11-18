package io.arkstud.wordbox_roberto.network

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import io.arkstud.wordbox_roberto.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


private const val TIME_OUT: Long = 100L
/* OkHttpClient to manage the requests time */
private val client: OkHttpClient = OkHttpClient.Builder()
    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
    .build()


/**
 * Create an instance using a default [Gson] instance for conversion. Encoding to JSON and
 * decoding from JSON (when no charset is specified by a header) will use UTF-8.
 */

/* Retrofit instance */
private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(GsonPConverterFactory(Gson()))
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
}

/**
 * Obtains a retrofit instance
 * @return [Retrofit]
 */
fun createNetworkClient() : Retrofit = retrofit
package io.arkstud.wordbox_roberto.network.person

import io.arkstud.wordbox_roberto.network.person.model.GetPersonsResponse
import kotlinx.coroutines.Deferred
import org.json.JSONObject
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface PersonApiService {

    @GET(".")
    fun getPersonsAsync(
        @Query("results") results: Int
        // @Query("callback") callback: String
    ): Deferred<GetPersonsResponse>

}
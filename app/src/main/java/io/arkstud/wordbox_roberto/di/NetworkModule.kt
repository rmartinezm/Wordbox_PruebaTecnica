package io.arkstud.wordbox_roberto.di

import io.arkstud.wordbox_roberto.network.createNetworkClient
import io.arkstud.wordbox_roberto.network.person.PersonApiService
import org.koin.dsl.module.module
import retrofit2.Retrofit

/* Retrofit init and creation of Services */
private val retrofit: Retrofit = createNetworkClient()
private val personApiService: PersonApiService = retrofit.create(PersonApiService::class.java)

/* Module of Retrofit Services */
val networkServicesModule = module {
    single { personApiService }
}
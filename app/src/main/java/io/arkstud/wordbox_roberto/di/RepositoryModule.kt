package io.arkstud.wordbox_roberto.di

import io.arkstud.wordbox_roberto.model.repository.internet_connection.InternetConnectionRepository
import io.arkstud.wordbox_roberto.model.repository.internet_connection.InternetConnectionRepositoryImpl
import io.arkstud.wordbox_roberto.model.repository.person.PersonRepository
import io.arkstud.wordbox_roberto.model.repository.person.PersonRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {
    single<PersonRepository> { PersonRepositoryImpl(get(), get()) }
    single<InternetConnectionRepository>(createOnStart = true) { InternetConnectionRepositoryImpl() }
}
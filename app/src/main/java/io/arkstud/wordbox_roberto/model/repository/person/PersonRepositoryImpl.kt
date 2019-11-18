package io.arkstud.wordbox_roberto.model.repository.person

import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.exception.NoInternetConnectionException
import io.arkstud.wordbox_roberto.model.repository.internet_connection.InternetConnectionRepository
import io.arkstud.wordbox_roberto.network.person.PersonApiService

class PersonRepositoryImpl(
    private val internetConnectionRepository: InternetConnectionRepository,
    private val personApiService: PersonApiService
) : PersonRepository {

    private val internetConnectionAvailable: Boolean get() =
        internetConnectionRepository.internetConnectionAvailable.value == true


    override suspend fun loadPersonsList(): List<Person> {
        if(internetConnectionAvailable){
            val response = personApiService.getPersonsAsync(
                GET_PERSONS_DEFAULT_RESULTS,
                GET_PERSONS_DEFAULT_CALLBACK
            ).await()
            return response.results.map { Person.fromPersonResponse(it) }
        } else throw NoInternetConnectionException()
    }

    companion object {
        private const val GET_PERSONS_DEFAULT_RESULTS: Int = 50
        private const val GET_PERSONS_DEFAULT_CALLBACK: String = "randomuserdata"
    }


}
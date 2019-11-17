package io.arkstud.wordbox_roberto.model.repository.person

import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.exception.NoInternetConnectionException

interface PersonRepository {

    /**
     * Obtains a [List] of persons.
     * @return [List] with the loaded persons.
     */
    @Throws(NoInternetConnectionException::class)
    suspend fun loadPersonsList() : List<Person>

}
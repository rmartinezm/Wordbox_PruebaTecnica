package io.arkstud.wordbox_roberto.ui.person_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.exception.NoInternetConnectionException
import io.arkstud.wordbox_roberto.model.manager.ResourceManager
import io.arkstud.wordbox_roberto.model.repository.person.PersonRepository
import io.arkstud.wordbox_roberto.ui.common.NetworkState
import kotlinx.coroutines.runBlocking

class PersonListViewModel(private val personRepository: PersonRepository) : ViewModel() {

    private val _persons: MutableLiveData<List<Person>> = MutableLiveData()
    val persons: LiveData<List<Person>> get() = _persons
    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: LiveData<NetworkState> get() = _networkState
    var message: String? = null

    /**
     *
     *
     */
    fun loadPersons() = runBlocking {
        _networkState.postValue(NetworkState.LOADING)
        try {
            val personsResponse = personRepository.loadPersonsList()
            _persons.postValue(personsResponse)
            _networkState.postValue(NetworkState.LOADED)
        } catch (NoInternetConnectionException: NoInternetConnectionException){
            message = ResourceManager().getString(R.string.no_internet_connection_error)
            _networkState.postValue(NetworkState.ERROR)
        }
    }

}
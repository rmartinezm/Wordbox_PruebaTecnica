package io.arkstud.wordbox_roberto.ui.person_action

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.ui.common.NetworkState

class PersonActionViewModel : ViewModel() {

    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: MutableLiveData<NetworkState> get() = _networkState

    /**
     *
     * @param person
     */
    fun launchLikeAction(person: Person){
        _networkState.postValue(NetworkState.LOADING)
        // Maybe in a future need be sent this information to an API
        person.liked = !person.liked
        _networkState.postValue(NetworkState.LOADED)
    }

}
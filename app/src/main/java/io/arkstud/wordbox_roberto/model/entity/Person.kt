package io.arkstud.wordbox_roberto.model.entity

import io.arkstud.wordbox_roberto.network.person.model.PersonPicture
import io.arkstud.wordbox_roberto.network.person.model.PersonResponse
import java.io.Serializable

data class Person (
    var title: String,
    var firstName: String? = null,
    var lastName: String? = null,
    var gender: Gender,
    var email: String,
    var picture: PersonPicture
) : Serializable {

    val fullName: String get() = "$title $firstName $lastName"

    companion object {
        fun fromPersonResponse(it: PersonResponse) = Person (
            it.name.title,
            it.name.first,
            it.name.last,
            Gender.fromString(it.gender),
            it.email,
            it.picture
        )
    }

    /**
     *
     */
    enum class Gender : Serializable {
        MALE,
        FEMALE,
        UNKNOWN;

        companion object {
            fun fromString(value: String?) = when(value){
                "male", "MALE" -> MALE
                "female", "FEMALE" -> FEMALE
                else -> UNKNOWN
            }
        }

    }

}
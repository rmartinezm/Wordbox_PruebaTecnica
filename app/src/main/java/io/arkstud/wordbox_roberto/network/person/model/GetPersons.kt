package io.arkstud.wordbox_roberto.network.person.model

import java.io.Serializable

data class GetPersonsResponse (
    var results: List<PersonResponse>,
    var info: Info
)

data class Info (
    var seed: String,
    var results: Int,
    var page: Int,
    var version: String
)

data class PersonResponse (
    var name: PersonName,
    var gender: String,
    var email: String,
    var picture: PersonPicture
)

data class PersonPicture (
    var thumbnail: String? = null,
    var medium: String? = null,
    var large: String? = null
) : Serializable

data class PersonName (
    var title: String,
    var first: String,
    var last: String
)
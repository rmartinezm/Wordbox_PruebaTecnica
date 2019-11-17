package io.arkstud.wordbox_roberto.model.manager

import android.content.Context
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

class ResourceManager : KoinComponent {

    private val context: Context by inject()

    fun getString(resource: Int): String? = context.getString(resource)

}
package io.arkstud.wordbox_roberto.model.util

import android.os.Handler
import android.view.View

fun setOnActionClickListener(view: View?, time: Long = 500L, action: () -> Unit){
    view?.setOnClickListener {
        action()
        view.setOnClickListener(null)
        Handler().postDelayed({ setOnActionClickListener(view, time, action) }, time)
    }
}


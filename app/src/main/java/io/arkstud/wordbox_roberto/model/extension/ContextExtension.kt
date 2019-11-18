package io.arkstud.wordbox_roberto.model.extension

import android.content.Context
import android.content.Intent
import android.os.Bundle

/**
 *
 *
 */
fun Context.navigateTo(
    activityClass: Class<*>,
    clearTop: Boolean = false,
    args: Bundle? = null
) {
    val intent = Intent(this, activityClass)
    if(clearTop)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    if(args != null)
        intent.putExtras(args)
    startActivity(intent)
}
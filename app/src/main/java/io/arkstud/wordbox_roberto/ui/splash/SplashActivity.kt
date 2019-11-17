package io.arkstud.wordbox_roberto.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.ui.person_list.PersonListActivity

class SplashActivity : AppCompatActivity() {

    /* */
    private val delayMills: Long = 1200L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ navigateTo(PersonListActivity::class.java) }, delayMills)

    }

    /**
     *
     *
     */
    private fun navigateTo(activityClass: Class<*>) {
        val intent = Intent(this, activityClass)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)

    }
}

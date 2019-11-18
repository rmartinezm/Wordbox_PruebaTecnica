package io.arkstud.wordbox_roberto.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.extension.navigateTo
import io.arkstud.wordbox_roberto.ui.person_list.PersonListActivity

class SplashActivity : AppCompatActivity() {

    /* */
    private val delayMills: Long = 1200L

    /**
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            navigateTo(PersonListActivity::class.java, clearTop = true)
        }, delayMills)
    }

}

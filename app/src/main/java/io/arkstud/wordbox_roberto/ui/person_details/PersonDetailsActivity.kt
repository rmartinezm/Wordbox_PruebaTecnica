package io.arkstud.wordbox_roberto.ui.person_details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.util.PERSON
import io.arkstud.wordbox_roberto.model.util.glide.getDefaultLoaderOptions
import kotlinx.android.synthetic.main.activity_person_details.*

class PersonDetailsActivity : AppCompatActivity() {

    private lateinit var person: Person

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        person = intent.getSerializableExtra(PERSON) as Person
        initializeViews()
    }

    private fun initializeViews(){
        setPersonData()
    }

    private fun setPersonData(){
        tvFullName.text = person.fullName
        tvEmail.text = person.email
        Glide.with(this)
            .load(person.picture.large)
            .apply(getDefaultLoaderOptions(this))
            .into(ivPhoto)
    }

}

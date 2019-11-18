package io.arkstud.wordbox_roberto.ui.person_details

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.util.PERSON
import io.arkstud.wordbox_roberto.model.util.PERSON_LIKE
import io.arkstud.wordbox_roberto.model.util.glide.getDefaultLoaderOptions
import io.arkstud.wordbox_roberto.model.util.setOnActionClickListener
import io.arkstud.wordbox_roberto.ui.common.NetworkState
import io.arkstud.wordbox_roberto.ui.person_action.PersonActionViewModel
import kotlinx.android.synthetic.main.activity_person_details.*
import org.koin.android.viewmodel.ext.android.viewModel

class PersonDetailsActivity : AppCompatActivity() {

    /* */
    private lateinit var person: Person
    /* */
    private val personActionViewModel: PersonActionViewModel by viewModel()

    /**
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_details)
        person = intent.getSerializableExtra(PERSON) as Person
        initializeViews()
        observeViewModel()
    }

    /**
     *
     *
     */
    private fun initializeViews(){
        supportActionBar?.title = getString(R.string.person_profile, person.firstName)
        setOnActionClickListener(ivLike){ personActionViewModel.launchLikeAction(person) }
        setPersonData()
    }

    /**
     *
     *
     */
    private fun observeViewModel(){
       personActionViewModel.networkState.observe(this, Observer {
           when(it){
               NetworkState.LOADING -> progressBar.visibility = View.VISIBLE
               NetworkState.LOADED -> {
                   ivLike?.setImageResource(
                       if(person.liked) R.drawable.ic_fill_like else R.drawable.ic_border_like
                   )
                   progressBar.visibility = View.GONE
               }
               else -> { /* PASS */ }
           }
       })
    }

    /**
     *
     *
     */
    private fun setPersonData(){
        tvFullName.text = person.fullName
        tvPhone?.text = person.phone
        tvFirstName?.text = person.firstName
        tvLastName?.text = person.lastName
        tvEmail?.text = person.email

        when(person.gender){
            Person.Gender.MALE -> {
                ivGender?.setImageResource(R.drawable.ic_man)
                ivCoverColor?.setImageResource(R.color.colorCoverMale)
            }
            Person.Gender.FEMALE -> {
                ivGender?.setImageResource(R.drawable.ic_woman)
                ivCoverColor?.setImageResource(R.color.colorCoverFemale)
            }
            Person.Gender.UNKNOWN -> {
                ivCoverColor?.setImageResource(R.color.colorCoverMale)
                ivGender?.setImageDrawable(null)
            }
        }
        tvGender?.text = person.gender.toString()

        Glide.with(this)
            .load(person.picture.large)
            .apply(getDefaultLoaderOptions(this))
            .apply(RequestOptions.circleCropTransform())
            .into(ivPhoto)
        ivLike?.setImageResource(
            if(person.liked) R.drawable.ic_fill_like else R.drawable.ic_border_like
        )
    }

    /**
     *
     *
     */
    override fun onBackPressed() {
        val resultIntent = Intent().apply { putExtra(PERSON_LIKE, person.liked) }
        setResult(Activity.RESULT_OK, resultIntent)
        super.onBackPressed()
    }

}

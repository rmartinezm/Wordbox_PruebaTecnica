package io.arkstud.wordbox_roberto.ui.person_list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.util.PERSON
import io.arkstud.wordbox_roberto.ui.person_details.PersonDetailsActivity
import io.arkstud.wordbox_roberto.ui.person_list.adapter.PersonAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class PersonListActivity : AppCompatActivity() {

    /* */
    private val personListViewModel: PersonListViewModel by viewModel()

    /**
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()
        observeViewModel()
        personListViewModel.loadPersons()
    }

    /**
     *
     *
     */
    private fun initializeViews() {
        recyclerViewPersons?.layoutManager = LinearLayoutManager(this)

    }

    /**
     *
     *
     */
    private fun observeViewModel() {
        personListViewModel.persons.observe(this, Observer { updatePersonsList(it) })
        personListViewModel.networkState.observe(this, Observer {
            when(it){
                PersonListViewModel.NetworkState.LOADING -> {
                    progressBar?.visibility = View.VISIBLE
                    tvMessage.text = getString(R.string.loading)
                    tvMessage?.visibility = View.VISIBLE
                }
                PersonListViewModel.NetworkState.ERROR -> {
                    progressBar?.visibility = View.GONE
                    tvMessage.text = personListViewModel.message
                    tvMessage?.visibility = View.VISIBLE
                }
                PersonListViewModel.NetworkState.LOADED, null -> {
                    progressBar?.visibility = View.GONE
                    tvMessage?.visibility = View.GONE
                }
            }
        })
    }



    /**
     *
     * @param list
     */
    private fun updatePersonsList(list: List<Person>) {
        if(list.isEmpty()) {
            tvMessage?.text = getString(R.string.empty_persons_list)
            tvMessage?.visibility = View.VISIBLE
            recyclerViewPersons?.adapter = null
        } else {
            tvMessage?.visibility = View.GONE
            recyclerViewPersons?.adapter = PersonAdapter(list) { navigateToPersonDetails(it) }
        }
    }

    /**
     *
     * @param person
     */
    private fun navigateToPersonDetails(person: Person) {
        val intent = Intent(this, PersonDetailsActivity::class.java).apply {
            putExtra(PERSON, person)
        }
        startActivity(intent)
    }

}

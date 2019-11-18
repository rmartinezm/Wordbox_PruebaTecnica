package io.arkstud.wordbox_roberto.ui.person_list

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.util.PERSON
import io.arkstud.wordbox_roberto.model.util.PERSON_LIKE
import io.arkstud.wordbox_roberto.model.util.REQUEST_CODE_LIKE
import io.arkstud.wordbox_roberto.model.util.setOnActionClickListener
import io.arkstud.wordbox_roberto.ui.common.NetworkState
import io.arkstud.wordbox_roberto.ui.person_action.PersonActionViewModel
import io.arkstud.wordbox_roberto.ui.person_details.PersonDetailsActivity
import io.arkstud.wordbox_roberto.ui.person_list.adapter.PersonAdapter
import kotlinx.android.synthetic.main.activity_person_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class PersonListActivity : AppCompatActivity() {

    /* */
    private val personListViewModel: PersonListViewModel by viewModel()
    private val personActionViewModel: PersonActionViewModel by viewModel()
    private var clickedPerson: Person? = null

    /**
     *
     * @param savedInstanceState
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person_list)
        initializeViews()
        observeViewModel()
        loadPersons()
    }

    /**
     *
     *
     */
    private fun initializeViews() {
        supportActionBar?.title = getString(R.string.person_list)
        recyclerViewPersons?.layoutManager = LinearLayoutManager(this)
        setOnActionClickListener(btnTryAgain) { loadPersons() }
        swipeRefreshLayoutPersons?.setOnRefreshListener { loadPersons() }
    }

    /**
     *
     *
     */
    private fun loadPersons() = CoroutineScope(Dispatchers.IO).launch {
        personListViewModel.loadPersons()
    }

    /**
     *
     *
     */
    private fun observeViewModel() {
        personListViewModel.persons.observe(this, Observer { updatePersonsList(it) })
        personListViewModel.networkState.observe(this, Observer {
            when(it){
                NetworkState.LOADING -> {
                    linearLayoutMessage?.visibility = View.GONE
                    progressBar?.visibility = View.VISIBLE
                }
                NetworkState.ERROR -> {
                    progressBar?.visibility = View.GONE
                    updatePersonsList(listOf())
                    tvMessage.text = personListViewModel.message
                    linearLayoutMessage?.visibility = View.VISIBLE
                }
                NetworkState.LOADED, null -> {
                    progressBar?.visibility = View.GONE
                    linearLayoutMessage?.visibility = View.GONE
                }
            }
        })
    }

    /**
     *
     * @param list
     */
    private fun updatePersonsList(list: List<Person>) {
        swipeRefreshLayoutPersons?.isRefreshing = false
        if(list.isEmpty()) {
            tvMessage?.text = getString(R.string.empty_persons_list)
            tvMessage?.visibility = View.VISIBLE
            recyclerViewPersons?.adapter = null
        } else {
            tvMessage?.visibility = View.GONE
            recyclerViewPersons?.adapter = PersonAdapter(
                list,
                onPersonActionClickListener = { person, viewHolder ->
                    clickedPerson = person
                    navigateToPersonDetails(person, viewHolder)
                },
                onLikePersonActionClickListener = {
                    personActionViewModel.launchLikeAction(it)
                    recyclerViewPersons?.adapter?.notifyDataSetChanged()
                }
            )
        }
    }

    /**
     *
     * @param person
     * @param viewHolder
     */
    private fun navigateToPersonDetails(person: Person, viewHolder: PersonAdapter.ViewHolder) {
        val intent = Intent(this, PersonDetailsActivity::class.java).apply {
            putExtra(PERSON, person)
        }
        val options: ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            viewHolder.ivPhoto ?: return,
            getString(R.string.transition_iv_photo)
        )
        startActivityForResult(intent, REQUEST_CODE_LIKE, options.toBundle())
    }

    /**
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_LIKE && resultCode == Activity.RESULT_OK){
            val likeValue = data?.getBooleanExtra(PERSON_LIKE, false)
            clickedPerson?.liked = likeValue ?: false
            recyclerViewPersons?.adapter?.notifyDataSetChanged()
        }
    }

}

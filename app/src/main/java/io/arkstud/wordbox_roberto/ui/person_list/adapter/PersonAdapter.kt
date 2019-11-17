package io.arkstud.wordbox_roberto.ui.person_list.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.util.glide.getDefaultLoaderOptions
import io.arkstud.wordbox_roberto.model.util.setOnActionClickListener

/**
 *
 * @param persons
 * @param personClickListener
 */
class PersonAdapter(
    private val persons: List<Person>,
    private val personClickListener: (person: Person) -> Unit
): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    private lateinit var context: Context

    /**
     *
     * @param itemView
     */
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvFullName: TextView? = itemView.findViewById(R.id.tvFullName)
        val tvGender: TextView? = itemView.findViewById(R.id.tvGender)
        val ivPhoto: ImageView = itemView.findViewById(R.id.ivPhoto)
    }

    /**
     *
     * @return [Int]
     */
    override fun getItemCount(): Int = persons.size

    /**
     *
     * @param holder
     * @param position
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = persons[position]
        with(holder) {
            tvFullName?.text = person.fullName
            tvGender?.text = person.gender.toString()
            Glide.with(context)
                .load(person.picture.medium)
                .apply(getDefaultLoaderOptions(context))
                .into(ivPhoto)
            setOnActionClickListener(itemView){ personClickListener(person) }
        }
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return [ViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = with(parent.context) {
        context = this
        ViewHolder(LayoutInflater.from(this).inflate(R.layout.adapter_person, parent, false))

    }

}

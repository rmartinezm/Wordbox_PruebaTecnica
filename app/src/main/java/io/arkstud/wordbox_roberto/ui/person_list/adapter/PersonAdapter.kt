package io.arkstud.wordbox_roberto.ui.person_list.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.arkstud.wordbox_roberto.R
import io.arkstud.wordbox_roberto.model.entity.Person
import io.arkstud.wordbox_roberto.model.util.glide.getDefaultLoaderOptions
import io.arkstud.wordbox_roberto.model.util.setOnActionClickListener

/**
 *
 * @param persons
 * @param onPersonActionClickListener
 */
class PersonAdapter(
    private val persons: List<Person>,
    private val onPersonActionClickListener: (person: Person, viewHolder: ViewHolder) -> Unit,
    private val onLikePersonActionClickListener: (person: Person) -> Unit
): RecyclerView.Adapter<PersonAdapter.ViewHolder>() {

    /* */
    private lateinit var context: Context

    /**
     *
     * @param itemView
     */
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tvFullName: TextView? = itemView.findViewById(R.id.tvFullName)
        val tvPhone: TextView? = itemView.findViewById(R.id.tvPhone)
        val ivGender: ImageView? = itemView.findViewById(R.id.ivGender)
        val ivPhoto: ImageView? = itemView.findViewById(R.id.ivPhoto)
        val ivLike: ImageView? = itemView.findViewById(R.id.ivLike)
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
            tvPhone?.text = person.phone
            ivGender?.setImageResource(when(person.gender){
                Person.Gender.MALE -> R.drawable.ic_man
                Person.Gender.FEMALE -> R.drawable.ic_woman
                Person.Gender.UNKNOWN -> android.R.color.white
            })
            ivLike?.setImageResource(
                if(person.liked) R.drawable.ic_fill_like else R.drawable.ic_border_like
            )
            Glide.with(context)
                .load(person.picture.large)
                .apply(getDefaultLoaderOptions(context))
                .apply(RequestOptions.circleCropTransform())
                .into(ivPhoto ?: return)
            setOnActionClickListener(itemView){ onPersonActionClickListener(person, this) }
            setOnActionClickListener(ivLike){ onLikePersonActionClickListener(person) }
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

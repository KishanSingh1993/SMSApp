package `in`.ktechnos.smsapp.adapters

import `in`.ktechnos.smsapp.data.local.NoteEntity
import `in`.ktechnos.smsapp.databinding.NotesRowBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(private val onClick: (NoteEntity) -> Unit) : ListAdapter<NoteEntity, ContactsAdapter.MyHolder>(DiffutilCallBack) {

    object DiffutilCallBack: DiffUtil.ItemCallback<NoteEntity>(){
        override fun areItemsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: NoteEntity, newItem: NoteEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }
    inner class MyHolder(private val binding: NotesRowBinding,val onClick: (NoteEntity) -> Unit) : RecyclerView.ViewHolder(binding.root) {

        private var currentFlower: NoteEntity? = null

        init {
            itemView.setOnClickListener {
                currentFlower?.let {
                    onClick(it)
                }
            }
        }

        fun bind(post: NoteEntity?) {
            currentFlower = post
            binding.tvTitle.text = post?.noteTitle
            //binding.tvDescription.text = post?.noteDescription
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(NotesRowBinding.inflate(LayoutInflater.from(parent.context),parent,false),onClick)
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}
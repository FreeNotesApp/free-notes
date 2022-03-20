package com.github.freenote.ui.noteslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.freenote.R
import com.github.freenote.databinding.ItemNoteBinding
import com.github.freenote.domain.NoteDbEntity
import com.github.freenote.ui.utils.getNoteColorId

class NotesAdapter(
    private val clickListener: (NoteDbEntity) -> Unit
) : ListAdapter<NoteDbEntity, NotesAdapter.NoteViewHolder>(NotesDiff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(parent)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(getItem(position), clickListener)
    }

    fun submitListWithScroll(list: List<NoteDbEntity>, commitCallback: Runnable?) {
        if (!currentList.isNullOrEmpty() && !list.isNullOrEmpty() && currentList.find { it.id == list.first().id } == null) {
            super.submitList(list, commitCallback)
        } else {
            super.submitList(list)
        }
    }

    class NoteViewHolder(viewGroup: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(viewGroup.context).inflate(R.layout.item_note, viewGroup, false)
    ) {
        private val binding: ItemNoteBinding by viewBinding(ItemNoteBinding::bind)

        fun bind(note: NoteDbEntity, clickListener: (NoteDbEntity) -> Unit) {
            binding.itemNoteTvTitle.text = note.title
            binding.itemNoteTvText.text = note.text
            binding.root.setCardBackgroundColor(
                itemView.context.getColor(getNoteColorId(note.color))
            )
            binding.root.setOnClickListener {
                clickListener(note)
            }
        }
    }
}

class NotesDiff : DiffUtil.ItemCallback<NoteDbEntity>() {
    override fun areItemsTheSame(oldItem: NoteDbEntity, newItem: NoteDbEntity): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: NoteDbEntity, newItem: NoteDbEntity): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}
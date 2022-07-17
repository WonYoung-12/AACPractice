package com.example.aacpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.aacpractice.databinding.ContactItemBinding

class ContactAdapter(private var contacts: LiveData<List<Contact>>) :
    RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {
    private lateinit var listener: ItemViewClickListener

    inner class ContactViewHolder(private val binding: ContactItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Contact?) {
            binding.contact = item
            itemView.setOnClickListener {
                item?.let {
                    listener.onItemViewClick(it)
                }
            }
            itemView.setOnClickListener {
                item?.let {
                    listener.onItemViewLongClick(it)
                }
            }
        }

    }

    fun setListener(listener: ItemViewClickListener) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ContactItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contacts.value?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return contacts.value?.size ?: 0
    }
}
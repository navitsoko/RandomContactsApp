package com.example.contacts

import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class ContactCardRecyclerViewAdapter(
    private val onContactClick: (Contact) -> Unit    // Lambda for click handler
) :
    RecyclerView.Adapter<ContactCardViewHolder>() {
    private var contacts: List<Contact> = emptyList()

    override fun onCreateViewHolder(     //RecyclerView calls it when needs to display a new item in the list.
        parent: ViewGroup,
        viewType: Int
    ): ContactCardViewHolder {
        val layoutView =
            LayoutInflater.from(parent.context).inflate(R.layout.contact_card, parent, false)
        return ContactCardViewHolder(layoutView)
    }

    override fun onBindViewHolder(holder: ContactCardViewHolder, position: Int) {
        if (position < contacts.size) {
            val contact = contacts[position]
            holder.contactName.text = contact.name
            holder.contactEmail.text = contact.email

            Glide
                .with(holder.itemView)
                .load(contact.image)
                .centerCrop()
                .placeholder(R.drawable.ic_emotions)
                .into(holder.contactImage)

            holder.itemView.setOnClickListener {
                onContactClick.invoke(contact)    // Call the click handler
            }
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun updateContacts(newContacts: List<Contact>) {
        this.contacts = newContacts
        notifyDataSetChanged()     // Notify the RecyclerView of the change
    }
}

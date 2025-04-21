package com.example.contacts

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class ContactCardViewHolder(itemView: View)   //storing views from itemView
    : RecyclerView.ViewHolder(itemView) {
    var contactName: TextView = itemView.findViewById(R.id.contact_name)
    var contactEmail: TextView = itemView.findViewById(R.id.contact_email)
    var contactImage: ImageView = itemView.findViewById(R.id.contact_image)
}
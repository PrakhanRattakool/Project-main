package com.android.example.project.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.project.R
import com.android.example.project.database.Contact

class ListsAdapter : RecyclerView.Adapter<ListsAdapter.ViewHolder>() {

    var data = listOf<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = data[position]

        holder.bind(item)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.list_item, parent, false)

        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.name_text)
        val phoneText: TextView = itemView.findViewById(R.id.phone_text)


        fun bind(item: Contact) {
            val res = itemView.context.resources
            nameText.text = item.nameS.toString()
            phoneText.text = item.phone.toString()
        }
    }
}
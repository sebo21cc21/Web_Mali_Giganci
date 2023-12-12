package com.example.maligiganci

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ContactAdapter(private val kontakty: List<ContactClass>) : RecyclerView.Adapter<ContactAdapter.KontaktViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontaktViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_kontakt, parent, false)
        return KontaktViewHolder(view)
    }

    override fun onBindViewHolder(holder: KontaktViewHolder, position: Int) {
        val kontakt = kontakty[position]

        holder.email2.text = kontakt.childEmail

        // Set the child's name and image
        holder.childName.text = kontakt.childName
        Glide.with(holder.itemView.context).load(kontakt.childImage).into(holder.childImage)
    }

    override fun getItemCount() = kontakty.size
    class KontaktViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val email2: TextView = view.findViewById(R.id.email_kontaktu)
        val childName: TextView = view.findViewById(R.id.child_name) // Add this
        val childImage: ImageView = view.findViewById(R.id.child_image) // Add this
    }
}

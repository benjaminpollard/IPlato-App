package com.idea.group.iplato.views.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.idea.group.iplato.R
import com.idea.group.iplato.models.responce.RocketModel

class RocketsAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items: MutableList<RocketModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return RocketsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_item_rocket, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun addItem( newItems: List<RocketModel>){
        items.addAll(newItems)
        this.notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as RocketsViewHolder

        holder.country.text = items[position].country
        holder.name.text = items[position].name
        Glide.with(holder.image.context).load(items[position].images.first()).into(holder.image)
    }

    fun clearItems() {
        items.clear()
    }

    internal class RocketsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.row_item_rocket_name)
        val country: TextView =
            itemView.findViewById(R.id.row_item_rocket_country)
        val image: ImageView = itemView.findViewById(R.id.row_item_rocket_image)
    }

}
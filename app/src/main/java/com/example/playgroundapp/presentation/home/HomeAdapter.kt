package com.example.playgroundapp.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.playgroundapp.R
import com.example.playgroundapp.domain.entity.Character

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private val mutableItems = mutableListOf<Character>()

    fun submitItems(items: List<Character>) {
        mutableItems.clear()
        mutableItems.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mutableItems[position]
        holder.bind(model)
    }

    override fun getItemCount(): Int {
        return mutableItems.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val photoView = view.findViewById<ImageView>(R.id.home_item_image_photo)
        private val titleView = view.findViewById<TextView>(R.id.home_item_txt_title)
        private val descriptionView = view.findViewById<TextView>(R.id.home_item_txt_description)

        fun bind(model: Character) {
            Glide.with(itemView.context)
                .load(model.image)
                .into(photoView)

            titleView.text = model.name
            descriptionView.text = model.type
        }
    }
}
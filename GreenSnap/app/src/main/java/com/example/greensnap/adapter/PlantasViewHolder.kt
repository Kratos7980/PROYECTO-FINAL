package com.example.greensnap.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.ItemPlantasBinding

class PlantasViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    //Creo un binding de ItemPlantasBinding
    val binding = ItemPlantasBinding.bind(itemView)

    //Recupero los elementos del xml con el binding
    val imageItem: ImageView = binding.imageItem
    val nombrePlantaItem: TextView = binding.nombrePlantaItem
}
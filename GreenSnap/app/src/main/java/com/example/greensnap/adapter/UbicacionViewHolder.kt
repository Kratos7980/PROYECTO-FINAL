package com.example.greensnap.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.ItemUbicacionesBinding

class UbicacionViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  {

    val binding = ItemUbicacionesBinding.bind(itemView)

    val namePlanta:TextView = binding.titNamePlanta
    val categoryPlanta:TextView = binding.titCategoriaPlanta
    val imageItem = binding.imageItem
}
package com.example.greensnap.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.ItemCuidadosBinding

class ConsejosViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    val binding = ItemCuidadosBinding.bind(itemView)
    val imageItem: ImageView = binding.imageCuiItem
    val nombreConsejoItem: TextView = binding.nombreCuidadoItem

}
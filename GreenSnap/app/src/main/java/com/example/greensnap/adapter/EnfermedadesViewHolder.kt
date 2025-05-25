package com.example.greensnap.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.ItemCuidadosBinding

class EnfermedadesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val binding = ItemCuidadosBinding.bind(itemView)

    val imageItem: ImageView = binding.imageCuiItem
    val nombreEnfermedad = binding.nombreCuidadoItem
}
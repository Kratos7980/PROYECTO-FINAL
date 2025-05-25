package com.example.greensnap.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.ItemCuidadosBinding

class CuidadosViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    //Creo un binding de ItemPlantasBinding
    val binding = ItemCuidadosBinding.bind(itemView)

    //Recupero los elementos del xml con el binding
    val imageItem: ImageView = binding.imageCuiItem
    val nombreCuidadoItem: TextView = binding.nombreCuidadoItem
}
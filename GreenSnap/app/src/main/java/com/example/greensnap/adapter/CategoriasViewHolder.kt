package com.example.greensnap.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.ItemCuidadosBinding

class CategoriasViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){

    val binding = ItemCuidadosBinding.bind(itemView)

    val imgCategoria = binding.imageCuiItem
    val nombreCategoria = binding.nombreCuidadoItem
}
package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.model.Categoria
import com.example.greensnap.view.PantallaPlantas

class CategoriasAdapter (private val listCategorias:ArrayList<Categoria>, private val context: Context) : RecyclerView.Adapter<CategoriasViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriasViewHolder {
        //Creo un layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)
        //
        return CategoriasViewHolder(layoutInflater.inflate(R.layout.item_categorias, parent, false))
    }

    override fun getItemCount(): Int {
        return listCategorias.size
    }

    override fun onBindViewHolder(holder: CategoriasViewHolder, position: Int) {

        val categoria:Categoria = listCategorias[position]

        holder.nombreCategoria.setText(categoria.nombre)

        val imageResourceId = holder.itemView.context.resources.getIdentifier(categoria.imagen,"drawable", holder.itemView.context.packageName)
        holder.imgCategoria.setImageResource(imageResourceId)

        holder.itemView.setOnClickListener(){
            val bundle: Bundle = Bundle()
            bundle.putSerializable("categoria", categoria)
            val intent: Intent = Intent(context, PantallaPlantas::class.java)
            intent.putExtra("data", bundle)
            context.startActivity(intent)
        }

    }


}
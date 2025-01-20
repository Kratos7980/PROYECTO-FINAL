package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.vista.PantallaPlantas
import com.example.greensnap.R
import com.example.greensnap.model.Planta

class PlantasAdapter (private val listPlantas:ArrayList<Planta>, private val context: Context) : RecyclerView.Adapter<PlantasViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantasViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return PlantasViewHolder(layoutInflater.inflate(R.layout.item_plantas, parent, false))
    }

    override fun getItemCount(): Int {
        return listPlantas.size
    }

    override fun onBindViewHolder(holder: PlantasViewHolder, position: Int) {

        val planta = listPlantas[position]
        holder.nombrePlantaItem.setText(planta.nombre_planta)
        holder.descripcionItem.setText(planta.tipo)
        val imageResourceId = holder.itemView.context.resources.getIdentifier(planta.imagen,"drawable", holder.itemView.context.packageName)
        holder.imageItem.setImageResource(imageResourceId)

        // Defino en onClick de los items
        holder.itemView.setOnClickListener{
            val intent:Intent = Intent(context, PantallaPlantas::class.java)
            val bundle:Bundle = Bundle()
            context.startActivity(intent)
        }

    }
}
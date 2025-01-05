package com.example.greensnap.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.dbconexion.PlantasHelper
import com.example.greensnap.model.Planta

class PlantasAdapter (private val listPlantas:ArrayList<Planta>) : RecyclerView.Adapter<PlantasViewHolder>(){



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
    }
}
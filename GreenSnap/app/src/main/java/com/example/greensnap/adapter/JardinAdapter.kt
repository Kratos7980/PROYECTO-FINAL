package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.model.Planta
import com.example.greensnap.view.PantallaCuidados

class JardinAdapter(private val listPlantas:ArrayList<Planta>, private val context: Context) : RecyclerView.Adapter<PlantasViewHolder>() {

    //Creo el viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantasViewHolder {

        //Creo un layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)
        //
        return PlantasViewHolder(layoutInflater.inflate(R.layout.item_plantas, parent, false))
    }

    //Recupero el tamaño de la lista
    override fun getItemCount(): Int {
        return listPlantas.size
    }

    //Pongo los datos en el viewHolder
    override fun onBindViewHolder(holder: PlantasViewHolder, position: Int) {
        // Recupero la planta
        val planta = listPlantas[position]

        // Pongo los datos en el viewHolder
        holder.nombrePlantaItem.setText(planta.nombre_planta)
        holder.descripcionItem.setText(planta.tipo)
        val imageResourceId = holder.itemView.context.resources.getIdentifier(
            planta.imagen,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.imageItem.setImageResource(imageResourceId)

        // Defino en onClick de los items
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, PantallaCuidados::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("planta", planta);
            context.startActivity(intent)
        }
        holder.itemView.setOnLongClickListener{
            println("hola")
            true
        }
    }
}
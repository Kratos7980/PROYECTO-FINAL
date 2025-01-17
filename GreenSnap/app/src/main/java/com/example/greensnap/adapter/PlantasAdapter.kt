package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.MainActivity
import com.example.greensnap.R
import com.example.greensnap.dbconexion.PlantasHelper
import com.example.greensnap.model.Planta
import com.example.greensnap.databinding.ActivityMainBinding as ActivityMainBinding

class PlantasAdapter (private val listPlantas:ArrayList<Planta>, private val context:Context) : RecyclerView.Adapter<PlantasViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantasViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)

        return PlantasViewHolder(layoutInflater.inflate(R.layout.item_plantas, parent, false))
    }

    override fun getItemCount(): Int {
        return listPlantas.size
    }

    override fun onBindViewHolder(holder: PlantasViewHolder, position: Int) {

        val planta = listPlantas[position]
        Log.e("carlos", "$planta")

        holder.nombrePlantaItem.setText(planta.nombre_planta)
        holder.descripcionItem.setText(planta.tipo)

        val imageResourceId = holder.itemView.context.resources.getIdentifier(planta.imagen,"drawable", holder.itemView.context.packageName)
        holder.imageItem.setImageResource(imageResourceId)

        //Defino el evento onClick

        holder.itemView.setOnClickListener{
            var intent: Intent = Intent(context, MainActivity::class.java)
//            var bundle: Bundle = Bundle()
            context.startActivity(intent)
        }
    }
}
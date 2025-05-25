package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.model.EnfermedadType
import com.example.greensnap.model.Planta
import com.example.greensnap.view.PantallaInfoCuidados

class EnfermedadesAdapter(private val listEnfermedades:ArrayList<EnfermedadType>, private val planta: Planta, private val context: Context) : RecyclerView.Adapter<EnfermedadesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnfermedadesViewHolder {
        //Creo un layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)
        return EnfermedadesViewHolder(layoutInflater.inflate(R.layout.item_cuidados, parent, false))
    }

    override fun getItemCount(): Int {
        return listEnfermedades.size
    }

    override fun onBindViewHolder(holder: EnfermedadesViewHolder, position: Int) {

        var enfermedad:EnfermedadType = listEnfermedades[position]

        holder.nombreEnfermedad.setText(enfermedad.nombre)

        val imageResourceId = holder.itemView.context.resources.getIdentifier(enfermedad.imagen,"drawable", holder.itemView.context.packageName)
        holder.imageItem.setImageResource(imageResourceId)

        holder.itemView.setOnClickListener(){
            var bundle= Bundle()
            bundle.putSerializable("tipo", enfermedad)
            bundle.putSerializable("planta", planta)
            var intent= Intent(context, PantallaInfoCuidados::class.java)
            intent.putExtra("datos", bundle)
            context.startActivity(intent)

        }
    }
}
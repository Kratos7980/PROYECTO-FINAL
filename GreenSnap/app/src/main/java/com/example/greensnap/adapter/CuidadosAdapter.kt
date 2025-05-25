package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.model.CuidadoType
import com.example.greensnap.model.Planta
import com.example.greensnap.view.PantallaInfoCuidados

class CuidadosAdapter (private val listCuidados:ArrayList<CuidadoType>, private val planta: Planta, private val context: Context) : RecyclerView.Adapter<CuidadosViewHolder>() {

    interface OnPlantaEliminadaListener

    //Creo el viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuidadosViewHolder {

        //Creo un layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)

        return CuidadosViewHolder(layoutInflater.inflate(R.layout.item_cuidados, parent, false))
    }

    //Pongo los datos en el viewHolder
    override fun onBindViewHolder(holder: CuidadosViewHolder, position: Int) {

        val cuidado:CuidadoType = listCuidados[position]

        holder.nombreCuidadoItem.setText(cuidado.nombre)

        val imageResourceId = holder.itemView.context.resources.getIdentifier(cuidado.imagen,"drawable", holder.itemView.context.packageName)
        holder.imageItem.setImageResource(imageResourceId)

        holder.itemView.setOnClickListener(){
            var bundle:Bundle = Bundle()
            bundle.putSerializable("tipo", cuidado)
            bundle.putSerializable("planta", planta)
            var intent= Intent(context, PantallaInfoCuidados::class.java)
            intent.putExtra("datos", bundle)
            context.startActivity(intent)

        }

    }

    //Recupero el tamaño de la lista
    override fun getItemCount(): Int {
        return listCuidados.size
    }
}
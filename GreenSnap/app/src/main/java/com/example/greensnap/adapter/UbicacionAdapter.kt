package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.dbconexion.UbicacionHelper
import com.example.greensnap.model.Planta
import com.example.greensnap.model.Ubicacion
import com.example.greensnap.viewModel.CategoriasViewModel

class UbicacionAdapter(private var ubicaciones: ArrayList<Ubicacion>,private val viewModel: CategoriasViewModel, private val context: Context) : RecyclerView.Adapter<UbicacionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UbicacionViewHolder {
        //Creo un layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)

        return UbicacionViewHolder(layoutInflater.inflate(R.layout.item_ubicaciones, parent, false))
    }

    override fun getItemCount(): Int {
        return ubicaciones.size
    }

    override fun onBindViewHolder(holder: UbicacionViewHolder, position: Int) {

        var ubicacion = ubicaciones[position]
        var categoria = viewModel.getCategoria(ubicacion.id_categoria)

        holder.namePlanta.setText(ubicacion.nombre_planta)
        holder.categoryPlanta.setText(categoria.nombre)

        var imagen: Bitmap = BitmapFactory.decodeByteArray(ubicacion.imagen,0,ubicacion.imagen.size)
        holder.imageItem.setImageBitmap(imagen)

        holder.itemView.setOnClickListener{
            // Crea un Uri con la latitud y la longitud
            val latitude = ubicacion.latitud
            val longitude = ubicacion.longitud
            val gmmIntentUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=$latitude%2C$longitude")
            Log.e("Carlos","$gmmIntentUri")
            // Crea un Intent con el Uri
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

            context.startActivity(mapIntent)
        }
    }

//    fun updateList(newList: ArrayList<Ubicacion>){
//        ubicaciones = newList
//        notifyDataSetChanged()
//    }
}
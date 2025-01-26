package com.example.greensnap.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaPrincipalBinding
import com.example.greensnap.dbconexion.JardinHelper
import com.example.greensnap.model.Planta
import com.example.greensnap.view.PantallaCuidados
import com.example.greensnap.view.PantallaPrincipal

class JardinAdapter(private val listPlantas:ArrayList<Planta>, private val context: Context) : RecyclerView.Adapter<JardinViewHolder>() {

    interface OnPlantaEliminadaListener

    //Creo el viewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JardinViewHolder {

        //Creo un layoutInflater
        val layoutInflater = LayoutInflater.from(parent.context)
        //
        return JardinViewHolder(layoutInflater.inflate(R.layout.item_jardin, parent, false))
    }

    //Pongo los datos en el viewHolder
    override fun onBindViewHolder(holder: JardinViewHolder, position: Int) {
        // Recupero la planta
        val planta = listPlantas[position]

        // Pongo los datos en el viewHolder
        holder.nombrePlantaItem.setText(planta.nombre_planta)
        val imageResourceId = holder.itemView.context.resources.getIdentifier(
            planta.imagen,
            "drawable",
            holder.itemView.context.packageName
        )
        holder.imageItem.setImageResource(imageResourceId)

        // Defino en onClick de los items

        // Abrir pantalla de cuidados
        holder.itemView.setOnClickListener {
            val intent: Intent = Intent(context, PantallaCuidados::class.java)
            val bundle: Bundle = Bundle()
            bundle.putSerializable("planta", planta);
            intent.putExtra("datos", bundle)
            context.startActivity(intent)
        }

        // Eliminar planta
        holder.itemView.setOnLongClickListener{
            val removedPosition = holder.adapterPosition
            Toast.makeText(holder.itemView.context, "Clicked: ${planta.nombre_planta}", Toast.LENGTH_SHORT).show()

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Borrado")
                .setMessage("¿Estás seguro de que deseas eliminar la planta ${planta.nombre_planta}?")
                .setPositiveButton("Borrar") { dialog, _ ->
                    // Eliminar de la base de datos
                    val eliminada = JardinHelper.removePlanta(planta, context)

                    if (eliminada) {
                        // Eliminar de la lista y actualizar el RecyclerView
                        val removedItem = listPlantas.removeAt(removedPosition)
                        notifyItemRemoved(removedPosition)
                        notifyItemRangeChanged(removedPosition, listPlantas.size)
                        Toast.makeText(holder.itemView.context, "Planta ${planta.nombre_planta} eliminada", Toast.LENGTH_SHORT).show()
                    } else {
                        // Manejar el error (por ejemplo, mostrar un mensaje)
                        Toast.makeText(holder.itemView.context, "Error al eliminar la planta", Toast.LENGTH_SHORT).show()
                    }
                    dialog.dismiss()
                }
                .setNegativeButton("Cancelar") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
            true
        }
    }

    //Recupero el tamaño de la lista
    override fun getItemCount(): Int {
        return listPlantas.size
    }
}
package com.example.greensnap.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.view.PantallaConsejos

class ConsejosAdapter (private val listConsejos:Array<String>, private val  context: Context) : RecyclerView.Adapter<ConsejosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConsejosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ConsejosViewHolder(layoutInflater.inflate(R.layout.item_cuidados, parent, false))
    }

    override fun getItemCount(): Int {
        return listConsejos.size
    }

    override fun onBindViewHolder(holder: ConsejosViewHolder, position: Int) {

        holder.nombreConsejoItem.setText(listConsejos[position])
        holder.imageItem.setImageResource(R.drawable.icon_info)

        holder.itemView.setOnClickListener{
            val bundle = Bundle()
            bundle.putString("info", listConsejos[position])
            val intent = Intent(context, PantallaConsejos::class.java)
            intent.putExtra("datos",bundle)
            context.startActivity(intent)
        }
    }
}
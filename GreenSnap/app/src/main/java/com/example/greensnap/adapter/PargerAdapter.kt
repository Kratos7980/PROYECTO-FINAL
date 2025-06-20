package com.example.greensnap.adapter

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.greensnap.fragments.ConsejosFragment
import com.example.greensnap.fragments.CuidadosFragment
import com.example.greensnap.fragments.EnfermedadesFragment
import com.example.greensnap.model.Planta
import com.example.greensnap.model.PlantaJardin

class PargerAdapter (private val planta:Planta?, fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

    //Creo una lista de fragmentos
    override fun getItemCount() = 3
    //Creo un metodo para crear un fragmento
    override fun createFragment(position: Int): Fragment {

        return when(position){
            0 -> {
                CuidadosFragment.newInstance(planta)

            }
            1 -> {
                EnfermedadesFragment.newInstance(planta)
            }
            2 -> {
                ConsejosFragment.newInstance()
            }
            else -> {
                throw Resources.NotFoundException("Position not found")
            }

        }

    }

}
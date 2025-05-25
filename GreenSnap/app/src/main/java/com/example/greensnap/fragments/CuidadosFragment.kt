package com.example.greensnap.fragments

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.adapter.CuidadosAdapter
import com.example.greensnap.adapter.JardinAdapter
import com.example.greensnap.databinding.FragmentCuidadosBinding
import com.example.greensnap.dbconexion.CategoriasHelper
import com.example.greensnap.dbconexion.CuidadosHelper
import com.example.greensnap.dbconexion.JardinHelper
import com.example.greensnap.model.Cuidado
import com.example.greensnap.model.CuidadoType
import com.example.greensnap.model.Planta
import com.example.greensnap.model.PlantaJardin
import com.example.greensnap.viewModel.CategoriasViewModel
import com.example.greensnap.viewModel.CuidadoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "plantaJardin"
private const val ARG_PARAM2 = "planta"

/**
 * A simple [Fragment] subclass.
 * Use the [CuidadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CuidadosFragment : Fragment() {

    private lateinit var planta: Planta
    private  lateinit var binding: FragmentCuidadosBinding
    private lateinit var myAdapter: CuidadosAdapter
    private val viewModelCuidado: CuidadoViewModel by viewModels()

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planta = it.getSerializable(ARG_PARAM2) as Planta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCuidadosBinding.inflate(inflater,container,false)
        val view = binding.root

        //Recupero la lista de cuidados
        val listCuidados:ArrayList<CuidadoType> = viewModelCuidado.getCuidados()

        //Muestro la lista de plantas en el recycler view
        val rv: RecyclerView? = binding.rvItemsCuidados

        //Creo el adaptador
        myAdapter = CuidadosAdapter(listCuidados, planta, requireActivity())

        //Añado el adaptador al recycler view
        rv?.layoutManager = LinearLayoutManager(requireActivity())
        rv?.adapter = myAdapter

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment CuidadosFragment.
         */

        @JvmStatic
        fun newInstance(planta:Planta?) =
            CuidadosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM2, planta)
                }
            }
    }
}
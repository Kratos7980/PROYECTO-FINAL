package com.example.greensnap.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.databinding.FragmentEnfermedadesBinding
import com.example.greensnap.model.PlantaJardin
import com.example.greensnap.viewModel.EnfermedadesViewModel
import com.example.greensnap.adapter.EnfermedadesAdapter
import com.example.greensnap.model.Planta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "plantaJ"
private const val ARG_PARAM2 = "planta"

/**
 * A simple [Fragment] subclass.
 * Use the [EnfermedadesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnfermedadesFragment : Fragment() {

    private lateinit var planta: Planta
    private lateinit var binding: FragmentEnfermedadesBinding
    private lateinit var myAdapter: EnfermedadesAdapter
    private val viewModelEnfermedad: EnfermedadesViewModel by viewModels()

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
        binding = FragmentEnfermedadesBinding.inflate(inflater,container,false)
        val view = binding.root

        val bundle = requireActivity().intent.getBundleExtra("datos")
        if(bundle != null){
            val planta = bundle?.getSerializable("planta") as Planta
        }
        val listEnfermedades = viewModelEnfermedad.getEnfermedadesPlanta(planta.cod_enfermedad)

        //Muestro la lista de plantas en el recycler view
        val rv: RecyclerView? = binding.rvItemsCuidados

        //Creo el adaptador
        myAdapter = EnfermedadesAdapter(listEnfermedades,planta ,requireActivity())

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
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnfermedadesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(planta:Planta?) =
            EnfermedadesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM2, planta)
                }
            }
    }
}

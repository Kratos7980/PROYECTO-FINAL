package com.example.greensnap.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.greensnap.R
import com.example.greensnap.databinding.FragmentEnfermedadesBinding
import com.example.greensnap.dbconexion.CategoriasHelper
import com.example.greensnap.dbconexion.CuidadosHelper
import com.example.greensnap.model.Cuidado
import com.example.greensnap.model.Planta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "planta"

/**
 * A simple [Fragment] subclass.
 * Use the [EnfermedadesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnfermedadesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var planta: Planta? = null
    private lateinit var binding: FragmentEnfermedadesBinding

    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            planta = it.getSerializable(ARG_PARAM1) as Planta
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnfermedadesBinding.inflate(inflater,container,false)
        val view = binding.root

        if(planta != null) {
            val codigo_cuidado = CategoriasHelper.obtenerCodigoCuidadosByIdCategoria(
                planta?.id_categoria,
                requireActivity()
            )
            val cuidado: Cuidado? = CuidadosHelper.obtenerCuidados(codigo_cuidado, requireActivity())

            if (codigo_cuidado != -1 && cuidado != null) {
                binding.titleE.text = getString(R.string.enfermedades)
                binding.contE.text = cuidado.enfermedades
            }else{
                Toast.makeText(requireActivity(), "No se pudiron recuperar los datos", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireActivity(), "Planta is null", Toast.LENGTH_SHORT).show()
        }

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
        fun newInstance(planta: Planta) =
            EnfermedadesFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, planta)
                }
            }
    }
}

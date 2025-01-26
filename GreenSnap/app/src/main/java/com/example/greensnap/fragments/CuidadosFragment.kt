package com.example.greensnap.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.greensnap.R
import com.example.greensnap.databinding.FragmentCuidadosBinding
import com.example.greensnap.dbconexion.CategoriasHelper
import com.example.greensnap.dbconexion.CuidadosHelper
import com.example.greensnap.model.Cuidado
import com.example.greensnap.model.Planta
import com.example.greensnap.viewModel.CategoriasViewModel
import com.example.greensnap.viewModel.CuidadoViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "planta"

/**
 * A simple [Fragment] subclass.
 * Use the [CuidadosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CuidadosFragment : Fragment() {

    private var planta: Planta? = null
    private  lateinit var binding: FragmentCuidadosBinding
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
        binding = FragmentCuidadosBinding.inflate(inflater,container,false)
        val view = binding.root

        val viewCategoria = CategoriasViewModel(requireActivity())
        val viewCuidado = CuidadoViewModel(requireActivity())

        if(planta != null) {
            val codigo_cuidado = viewCategoria.getIdCuidado(planta?.id_categoria)
            val cuidado: Cuidado? = viewCuidado.getCuidado(codigo_cuidado)

            if (codigo_cuidado != -1 && cuidado != null) {
                binding.titleC.text = getString(R.string.luz)
                binding.titleC2.text = getString(R.string.temperatura)
                binding.titleC3.text = getString(R.string.sustrato)
                binding.titleC4.text = getString(R.string.riego)
                binding.titleC5.text = getString(R.string.abono)
                binding.titleC6.text = getString(R.string.poda)
                binding.titleC7.text = getString(R.string.enfermedades)
                binding.titleC8.text = getString(R.string.transplante)
                binding.contC.text = cuidado.iluminacion
                binding.contC2.text = cuidado.temperatura
                binding.contC3.text = cuidado.sustrato
                binding.contC4.text = cuidado.riego
                binding.contC5.text = cuidado.abono
                binding.contC6.text = cuidado.poda
                binding.contC7.text = cuidado.enfermedades
                binding.contC8.text = cuidado.trasplante
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
         * @return A new instance of fragment CuidadosFragment.
         */

        @JvmStatic
        fun newInstance(planta: Planta) =
            CuidadosFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, planta)
                }
            }
    }
}
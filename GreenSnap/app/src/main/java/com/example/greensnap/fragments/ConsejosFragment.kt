package com.example.greensnap.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.greensnap.R
import com.example.greensnap.adapter.ConsejosAdapter
import com.example.greensnap.databinding.FragmentConsejosBinding
import com.example.greensnap.databinding.FragmentCuidadosBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ConsejosFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ConsejosFragment : Fragment() {

//    private var param1: String? = null
//    private var param2: String? = null
    private lateinit var binding: FragmentConsejosBinding
    private lateinit var myAdapter:ConsejosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentConsejosBinding.inflate(inflater,container,false)
        val view = binding.root

        val rv: RecyclerView? = binding.rvItemsConsejos

        myAdapter = ConsejosAdapter(resources.getStringArray(R.array.videos),requireActivity())

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
         * @return A new instance of fragment ConsejosFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            ConsejosFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
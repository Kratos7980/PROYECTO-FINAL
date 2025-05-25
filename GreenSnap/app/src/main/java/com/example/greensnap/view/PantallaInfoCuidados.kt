package com.example.greensnap.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaInfoCuidadosBinding
import com.example.greensnap.mediaController.MyMediaController
import com.example.greensnap.model.CuidadoType
import com.example.greensnap.model.EnfermedadType
import com.example.greensnap.model.Planta
import com.example.greensnap.viewModel.CuidadoViewModel
import com.example.greensnap.viewModel.EnfermedadesViewModel

class PantallaInfoCuidados : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaInfoCuidadosBinding
    private val viewModelCuidados: CuidadoViewModel by viewModels()
    private val viewModelEnfermedad: EnfermedadesViewModel by viewModels()
    private lateinit var planta: Planta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaInfoCuidadosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val txtInfo = binding.txtInfo
        val imagen = binding.imagePlantInfo
        var bundle = intent.getBundleExtra("datos")
        if (bundle != null) {
            val tipo = bundle.getSerializable("tipo")
            planta = bundle.getSerializable("planta") as Planta
            if (tipo is CuidadoType) {
                var cuidado = viewModelCuidados.getCuidadoPlanta(planta.cod_cuidado)
                txtInfo.setText(cuidado?.descripcion)
                when(tipo.id){
                    1 -> imagen?.setImageResource(R.drawable.info_iluminacion)
                    2 -> imagen?.setImageResource(R.drawable.info_temperatura)
                    3 -> imagen?.setImageResource(R.drawable.info_sustrato)
                    4 -> imagen?.setImageResource(R.drawable.info_riego)
                    5 -> imagen?.setImageResource(R.drawable.info_abono)
                    6 -> imagen?.setImageResource(R.drawable.info_poda)
                    7 -> imagen?.setImageResource(R.drawable.info_trasplante)
                    8 -> imagen?.setImageResource(R.drawable.info_propagacion)
                }
            }else if (tipo is EnfermedadType){
                var enfermedad = viewModelEnfermedad.getEnfermedadPlanta(planta.cod_enfermedad, tipo.id)
                txtInfo.setText(enfermedad?.descripcion)
                when(tipo.id){
                    1 -> imagen?.setImageResource(R.drawable.moho_blanco)
                    2 -> imagen?.setImageResource(R.drawable.flor_marchita)
                    3 -> imagen?.setImageResource(R.drawable.cicatrices)
                    4 -> imagen?.setImageResource(R.drawable.marchitamiento_hojas)
                    5 -> imagen?.setImageResource(R.drawable.hojas_amarillas)
                    6 -> imagen?.setImageResource(R.drawable.mariquitas)
//                    7 -> imagen?.setImageResource(R.drawable.info_enfermedad7)
//                    8 -> imagen?.setImageResource(R.drawable.info_enfermedad8)
//                    9 -> imagen?.setImageResource(R.drawable.info_enfermedad9)
                    10 -> imagen?.setImageResource(R.drawable.marchitamiento_planta)
//                    11 -> imagen?.setImageResource(R.drawable.info_enfermedad11)
                    12 -> imagen?.setImageResource(R.drawable.muerte_regresiva)
                    13 -> imagen?.setImageResource(R.drawable.cochinillas)
                    14 -> imagen?.setImageResource(R.drawable.pudricion_hojas)
                    15 -> imagen?.setImageResource(R.drawable.mancha_marron)
                    16 -> imagen?.setImageResource(R.drawable.falta_riego)
//                    17 -> imagen?.setImageResource(R.drawable.info_enfermedad17)
//                    18 -> imagen?.setImageResource(R.drawable.info_enfermedad18)
//                    19 -> imagen?.setImageResource(R.drawable.info_enfermedad19)
//                    20 -> imagen?.setImageResource(R.drawable.info_enfermedad20)
                    21 -> imagen?.setImageResource(R.drawable.quemaduras_hojas)
                    22 -> imagen?.setImageResource(R.drawable.marchitamiento_postfloracion)
                    23 -> imagen?.setImageResource(R.drawable.chinches_encaje)
                    24 -> imagen?.setImageResource(R.drawable.tizon_petalo)
                    25 -> imagen?.setImageResource(R.drawable.orugas)
                    26 -> imagen?.setImageResource(R.drawable.deficiencia_nutrientes)
                    27 -> imagen?.setImageResource(R.drawable.envejecimiento)
                    28 -> imagen?.setImageResource(R.drawable.escarabajo_hojas)
                    29 -> imagen?.setImageResource(R.drawable.gorgojos_hojas)
                    30 -> imagen?.setImageResource(R.drawable.antracnosis)
                    31 -> imagen?.setImageResource(R.drawable.punto_negro)
//                    32 -> imagen?.setImageResource(R.drawable.info_enfermedad32)
//                    33 -> imagen?.setImageResource(R.drawable.info_enfermedad33)
//                    34 -> imagen?.setImageResource(R.drawable.info_enfermedad34)
//                    35 -> imagen?.setImageResource(R.drawable.info_enfermedad35)
//                    36 -> imagen?.setImageResource(R.drawable.info_enfermedad36)
//                    37 -> imagen?.setImageResource(R.drawable.info_enfermedad37)
//                    38 -> imagen?.setImageResource(R.drawable.info_enfermedad38)
//                    39 -> imagen?.setImageResource(R.drawable.info_enfermedad39)
//                    40 -> imagen?.setImageResource(R.drawable.info_enfermedad40)
//                    41 -> imagen?.setImageResource(R.drawable.info_enfermedad41)
//                    42 -> imagen?.setImageResource(R.drawable.info_enfermedad42)
                    else -> imagen?.setImageResource(R.drawable.azalea_fuego)

                }
            }

        }
    }
}
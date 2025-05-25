package com.example.greensnap.model

import java.io.Serializable

data class Planta(
    var id:Int,
    var nombre_planta:String,
    var imagen:String,
    var descripcion:String,
    var id_categoria:Int,
    var cod_cuidado:Int,
    var cod_enfermedad:Int
):Serializable
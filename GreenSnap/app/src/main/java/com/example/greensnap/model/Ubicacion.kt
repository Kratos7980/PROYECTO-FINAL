package com.example.greensnap.model

import java.io.Serializable

data class Ubicacion(
    var nombre_planta:String,
    var id_categoria:Int,
    var imagen:ByteArray,
    var latitud: Double,
    var longitud: Double
):Serializable{
    constructor(latitud:Double,longitud:Double) : this("",0,ByteArray(0),latitud,longitud)
}

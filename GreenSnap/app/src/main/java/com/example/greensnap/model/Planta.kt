package com.example.greensnap.model

import java.io.Serializable

data class Planta(var id:Int, var nombre_planta:String, var tipo:String, var imagen:String, var id_categoria:Int):Serializable {}
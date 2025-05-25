package com.example.greensnap.model

import java.io.Serializable

data class Enfermedad(
    val codigo:Int,
    val descripcion:String,
    val id_tipo_enfermedad:Int
):Serializable

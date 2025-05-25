package com.example.greensnap.model

import java.io.Serializable

data class Cuidado(
    val codigo:Int,
    val descripcion:String,
    val id_tipo_cuidado:Int
):Serializable

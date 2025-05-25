package com.example.greensnap.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.greensnap.R
import com.example.greensnap.databinding.ActivityPantallaConsejosBinding
import com.example.greensnap.mediaController.MyMediaController

class PantallaConsejos : AppCompatActivity() {

    private lateinit var binding: ActivityPantallaConsejosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPantallaConsejosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.getBundleExtra("datos")
        val info = bundle?.getString("info")


        var video = binding.videoView2

        when(info){
            "¿Como saber cuanta luz necesita tu planta?" -> {
                video.setVideoPath("android.resource://" + packageName + "/" + R.raw.como_saber_cuanta_luz_necita_tu_planta)
            }
        }
        video.requestFocus()
        var mediaControls = MyMediaController(this,this, video)
        video.setMediaController(mediaControls)
        video.start()

    }
}
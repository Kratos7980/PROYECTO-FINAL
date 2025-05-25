package com.example.greensnap.mediaController

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.view.View
import android.widget.ImageButton
import android.widget.MediaController
import android.widget.VideoView
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.greensnap.R

class MyMediaController(context: Context, private val activity: Activity, private val videoView: VideoView) : MediaController(context)  {

    private var isFullScreen = false
    private lateinit var fullScreenButton: ImageButton

    override fun setAnchorView(view: View?) {
        super.setAnchorView(view)
        fullScreenButton = ImageButton(context)

        fullScreenButton.setImageResource(R.drawable.baseline_fullscreen_24)
        fullScreenButton.setBackgroundColor(0x00000000)

        val params = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        params.marginEnd = 24
        params.bottomMargin = 24
        params.gravity = android.view.Gravity.TOP or android.view.Gravity.END

        addView(fullScreenButton, params)
        fullScreenButton.setOnClickListener {
            toggleFullScreen()
        }

    }

    private fun toggleFullScreen() {
        if (isFullScreen){
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            hideSystemUI()
            val layoutParams = videoView.layoutParams
            layoutParams.height = LayoutParams.MATCH_PARENT
            layoutParams.width = LayoutParams.MATCH_PARENT
            videoView.layoutParams = layoutParams
            isFullScreen = false
        }
        else{
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            isFullScreen = true
        }
    }

    private fun hideSystemUI(){
        WindowCompat.setDecorFitsSystemWindows(activity.window,false)
        WindowInsetsControllerCompat(activity.window, activity.window.decorView).let{
            controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI(){
        WindowCompat.setDecorFitsSystemWindows(activity.window,true)
        WindowInsetsControllerCompat(activity.window, activity.window.decorView).show(WindowInsetsCompat.Type.systemBars())

    }
}
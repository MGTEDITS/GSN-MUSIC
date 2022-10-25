
package com.example.gsnmusic.kotlin

import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.Toast
import com.example.gsnmusic.R
import java.io.IOException



class LeitorActivity : AppCompatActivity() {

    private lateinit var runnable: Runnable
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leitor)


        val mediaPlayer = MediaPlayer.create(this,R.raw.playground)

        val btnplay = findViewById<ImageButton>(R.id.fab_play)
        val seekBar = findViewById<SeekBar>(R.id.sbprogress)

        seekBar.progress = 0

        seekBar.max = mediaPlayer.duration

        btnplay.setOnClickListener{
            if (!mediaPlayer.isPlaying){
                mediaPlayer.start()
                btnplay.setImageResource(R.drawable.pausa)
            }else{
                mediaPlayer.pause()
                btnplay.setImageResource(R.drawable.playrecycler)
            }
        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, pos: Int, changed: Boolean) {
                if (changed){
                    mediaPlayer.seekTo(pos)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {

            }
        })

        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)

        mediaPlayer.setOnCompletionListener {
            btnplay.setImageResource(R.drawable.playrecycler)
            seekBar.progress = 0
        }

    }


}
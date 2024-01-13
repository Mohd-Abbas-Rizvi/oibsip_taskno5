package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.stopwatch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding  //....///

    private var isRunning = false
    private var timerSeconds=0

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object:Runnable{
        override fun run(){
            timerSeconds++
            val hours =timerSeconds/3600
            val minutes = (timerSeconds%3600)/60
            val seconds=timerSeconds%60

            val time=String.format("%02d:%02d:%02d",hours, minutes, seconds)
            binding.timer.text=time

            handler.postDelayed(this,1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)//...//
        setContentView(binding.root)//....//
      //  setContentView(R.layout.activity_main)

        binding.stbtn.setOnClickListener{
            startTimer()
        }
        binding.hlbtn.setOnClickListener{
            holdTimer()
        }
        binding.stpbtn.setOnClickListener{
            stopTimer()
        }
    }
    private fun startTimer()
    {
        if(!isRunning){
            handler.postDelayed(runnable,1000)
            isRunning=true
            binding.stbtn.isEnabled=false
            binding.hlbtn.isEnabled=true
            binding.stpbtn.isEnabled=true
        }
    }

    private fun holdTimer() {
        if (isRunning) {
            handler.removeCallbacks(runnable)
            isRunning = false
            binding.stbtn.isEnabled = true
            binding.stbtn.text = "Resume"
            binding.hlbtn.isEnabled = false
            binding.stpbtn.isEnabled = true
        }
    }

    private fun stopTimer(){
       holdTimer()

        timerSeconds=0
        binding.timer.text="00:00:00"
        binding.stbtn.isEnabled = true
        binding.stpbtn.isEnabled=false
        binding.stbtn.text = "Start"
    }
}
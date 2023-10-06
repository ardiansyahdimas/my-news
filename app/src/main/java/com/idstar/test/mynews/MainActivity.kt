package com.idstar.test.mynews

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.idstar.test.mynews.databinding.ActivityMainBinding
import com.idstar.test.mynews.home.HomeActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val handler = Handler(Looper.getMainLooper())
    private val delayMillis: Long = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startHandlerLoop()
    }

    private fun startHandlerLoop() {
        handler.postDelayed({
           startActivity(Intent(this@MainActivity, HomeActivity::class.java))
           finish()
        }, delayMillis)
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}
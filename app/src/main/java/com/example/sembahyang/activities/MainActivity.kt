package com.example.sembahyang.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sembahyang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onActions()
    }

    private fun onActions() {
        let {

            binding.cvJadwalSholat.setOnClickListener {
                val intent = Intent(this@MainActivity, JadwalSholatActivity::class.java)
                startActivity(intent)
            }

            binding.cvAlquran.setOnClickListener {
                val intent = Intent(this@MainActivity, AlquranActivity::class.java)
                startActivity(intent)
            }

            binding.cvDoa.setOnClickListener {
                val intent = Intent(this@MainActivity, DoaActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
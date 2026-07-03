package com.example.layoutpractice

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.layoutpractice.databinding.ActivityHomeScreenBinding

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: ActivityHomeScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
        val savedName = sharedPreferences.getString("userName","no name")
        val intentName = intent.getStringExtra("name")
        binding.txtName.text = intentName
        binding.txtName.text = "Hello $savedName"

        binding.btnLogout.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            startActivity(Intent(this, LogIn::class.java))
            finish()
        }
    }
}
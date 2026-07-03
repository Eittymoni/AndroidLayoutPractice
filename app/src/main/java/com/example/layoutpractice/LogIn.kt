package com.example.layoutpractice

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.layoutpractice.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    private lateinit var binding: ActivityLogInBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLogInBinding.inflate(layoutInflater)

        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs",MODE_PRIVATE)
        val savedName = sharedPreferences.getString("userName",null)

        binding.etEmail.setText(savedName)
//        if(savedName != null){
//            Toast.makeText(this,"Data exit", Toast.LENGTH_SHORT).show()
//
//            startActivity(Intent(this, HomeScreen::class.java))
//            finish()
//
//        }

binding.etEmail.addTextChangedListener(object: TextWatcher{
    override fun afterTextChanged(setTheText: Editable?) {
        sharedPreferences.edit {
            putString("userName", setTheText.toString())
        }
    }

    override fun beforeTextChanged(
        s: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {

    }

    override fun onTextChanged(
        s: CharSequence?,
        start: Int,
        before: Int,
        count: Int
    ) {

    }


})

//        binding.btnLogin.setOnClickListener {
//            Toast.makeText(this,"Log in successful", Toast.LENGTH_SHORT).show()
//            startActivity(Intent(this, HomeScreen::class.java))
//            finish()
//        }

        binding.btnLogin.setOnClickListener {
            val name = binding.etEmail.text.toString()

            if(name.isEmpty()){
                Toast.makeText(this,"Name required", Toast.LENGTH_SHORT).show()
            }

            val editor = sharedPreferences.edit()
            editor.putString("userName",name)
            editor.apply()

            val intent = Intent(this, HomeScreen::class.java)
            intent.putExtra("name",name)
            startActivity(intent)
        }
    }
}
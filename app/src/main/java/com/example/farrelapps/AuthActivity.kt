package com.example.farrelapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farrelapps.databinding.ActivityAuthBinding
import com.example.farrelapps.databinding.ActivityThirdBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                enableEdgeToEdge()
                binding = ActivityAuthBinding.inflate(layoutInflater)
                setContentView(binding.root)
                ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
                    val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                    insets
                }

                val sharedPref = getSharedPreferences("user_pref", MODE_PRIVATE)

//                val isLogin = sharedPref.getBoolean("isLogin", false)
//                if (isLogin) {
//                    val intent = Intent(this, MainActivity::class.java)
//                    startActivity(intent)
//                    finish()
//                }
                binding.btnlogin.setOnClickListener {
                val username = binding.btnusername.text.toString()
                val password = binding.btnpassword.text.toString()
                if (username == password) {
                    val editor = sharedPref.edit()
                    editor.putBoolean("isLogin", true)
                    editor.putString("username",username)
                    editor.apply()

                    Toast.makeText(this, "Login berhasil", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                } else {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Konfirmasi")
                        .setMessage("Coba lagi")
                        .setNegativeButton("ok") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }


                }
    }
}
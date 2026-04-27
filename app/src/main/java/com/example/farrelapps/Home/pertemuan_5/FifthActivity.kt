package com.example.farrelapps.Home.pertemuan_5

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.farrelapps.R
import com.example.farrelapps.databinding.ActivityFifthBinding
import com.google.android.material.snackbar.Snackbar

class FifthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFifthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFifthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Setup Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false) // Matikan judul bawaan karena kita pakai TextView custom di tengah
            setDisplayHomeAsUpEnabled(true)
        }

        // Aksi Tombol Web
        binding.btnWebView.setOnClickListener {
            val intent = Intent(this, WebViewActivity::class.java)
            startActivity(intent)
        }

        // Aksi Floating Action Button (FAB)
        binding.fabAction.setOnClickListener {
            Snackbar.make(binding.root, "Tombol FAB Berhasil Ditekan!", Snackbar.LENGTH_SHORT).show()
        }
    }

    // Menampilkan Option Menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // Aksi saat item menu ditekan
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressedDispatcher.onBackPressed()
                true
            }
            R.id.action_search -> {
                Toast.makeText(this, "Search Clicked", Toast.LENGTH_SHORT).show()
                true
            }
            // Menangani ceklis pada Sub-Menu
            R.id.sort_az -> {
                item.isChecked = true
                Toast.makeText(this, "Diurutkan dari A - Z", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.sort_za -> {
                item.isChecked = true
                Toast.makeText(this, "Diurutkan dari Z - A", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
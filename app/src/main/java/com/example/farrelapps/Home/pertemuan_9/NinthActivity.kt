package com.example.farrelapps.Home.pertemuan_9

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.farrelapps.databinding.ActivityNinthBinding
import com.example.farrelapps.utils.NotificationHelper
import com.example.farrelapps.utils.PermissionHelper
import com.google.android.material.chip.Chip

class NinthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNinthBinding

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(this, "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        binding = ActivityNinthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(this, permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            title = "Latihan Pertemuan 9"
            setDisplayHomeAsUpEnabled(true)
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        // Logika Chip
        binding.chipGroupFilter.setOnCheckedStateChangeListener { group, checkedIds ->
            val selectedChipId = checkedIds.firstOrNull()
            if (selectedChipId != null) {
                val chip = group.findViewById<Chip>(selectedChipId)
                Toast.makeText(this, "Filter: ${chip.text}", Toast.LENGTH_SHORT).show()
            }
        }

        // Logika Validasi Tombol Login (Email & Phone)
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val phone = binding.etPhone.text.toString()

            var isValid = true

            // Validasi Email
            if (email.isEmpty()) {
                binding.textInputLayout.error = "Email tidak boleh kosong!"
                isValid = false
            } else {
                binding.textInputLayout.error = null
            }

            // Validasi Nomor Telepon
            if (phone.isEmpty()) {
                binding.tlPhone.error = "Nomor telepon tidak boleh kosong!"
                isValid = false
            } else {
                binding.tlPhone.error = null
            }

            // Jika semua form terisi
            if (isValid) {
                Toast.makeText(this, "Berhasil! Email: $email, No HP: $phone", Toast.LENGTH_LONG).show()

            }

            NotificationHelper.showNotification(
                this, //Jika panggil di fragment maka requireContext()
                "email Anda",
                "Halo $email, email anda tersimpan",
                intent
            )
        }
        }
    }

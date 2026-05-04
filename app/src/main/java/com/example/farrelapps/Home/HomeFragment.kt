package com.example.farrelapps.Home

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.farrelapps.AuthActivity
import com.example.farrelapps.Home.pertemuan_2.SecondActivity
import com.example.farrelapps.Home.pertemuan_3.ThirdActivity
import com.example.farrelapps.Home.pertemuan_4.FourthActivity
import com.example.farrelapps.Home.pertemuan_5.WebViewActivity // Sesuaikan jika Fifth itu WebView
import com.example.farrelapps.Home.pertemuan_7.SeventhActivity
import com.example.farrelapps.Home.pertemuan_9.NinthActivity
import com.example.farrelapps.databinding.FragmentHomeBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPref = requireContext().getSharedPreferences("user_pref", MODE_PRIVATE)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Beranda FDPR"
        }

        // --- NAVIGASI PERTEMUAN (Menggunakan ID btn yang baru) ---

        // Pertemuan 2
        binding.btnMenuP2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }

        // Pertemuan 3
        binding.btnMenuP3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }

        // Pertemuan 4 (Dengan Data Intent)
        binding.btnMenuP4.setOnClickListener {
            val intent = Intent(requireContext(), FourthActivity::class.java)
            intent.putExtra("name", "Politeknik Caltex Riau")
            intent.putExtra("from", "Rumbai")
            intent.putExtra("age", 25)
            startActivity(intent)
        }

        // Pertemuan 5 (WebView)
        binding.btnMenuP5.setOnClickListener {
            startActivity(Intent(requireContext(), WebViewActivity::class.java))
        }

        // Pertemuan 7
        binding.btnMenuP7.setOnClickListener {
            startActivity(Intent(requireContext(), SeventhActivity::class.java))
        }

        // Pertemuan 9 (Latihan Material Design)
        binding.btnMenuP9.setOnClickListener {
            startActivity(Intent(requireContext(), NinthActivity::class.java))
        }

        // --- LOGIKA LOGOUT ---
        binding.btnLogout.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Konfirmasi Logout")
                .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
                .setPositiveButton("Ya, Keluar") { dialog, _ ->
                    dialog.dismiss()
                    sharedPref.edit { clear() }
                    val intent = Intent(requireContext(), AuthActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    requireActivity().finish()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                    Log.d("Info Dialog", "User membatalkan logout")
                }
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
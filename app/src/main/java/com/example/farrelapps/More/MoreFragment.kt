package com.example.farrelapps.More // Pastikan package ini sesuai

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.farrelapps.databinding.FragmentMoreBinding // Sesuaikan nama binding

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Membuat data list menggunakan Map seperti instruksi modul Tahap 4️⃣
    private val dataListWithDesc = listOf(
        mapOf("title" to "Kotlin", "desc" to "Bahasa untuk Android modern"),
        mapOf("title" to "Java", "desc" to "Bahasa OOP yang populer"),
        mapOf("title" to "Python", "desc" to "Bahasa yang mudah dipahami"),
        mapOf("title" to "C++", "desc" to "Bahasa pemrograman tingkat menengah"),
        mapOf("title" to "JavaScript", "desc" to "Bahasa utama untuk Web"),
        mapOf("title" to "Dart", "desc" to "Bahasa yang digunakan untuk Flutter"),
        mapOf("title" to "Swift", "desc" to "Bahasa untuk iOS modern"),
        mapOf("title" to "PHP", "desc" to "Bahasa backend web legendaris")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Daftar Bahasa"
        }

        // Terapkan SimpleAdapter dengan layout simple_list_item_2 (Tahap 4️⃣)
        val adapter = SimpleAdapter(
            requireContext(),
            dataListWithDesc,
            android.R.layout.simple_list_item_2, // Menggunakan layout bawaan Android
            arrayOf("title", "desc"), // Mengambil key dari Map
            intArrayOf(android.R.id.text1, android.R.id.text2) // Dimasukkan ke ID bawaan Android
        )

        // Hubungkan listViewItems dengan adapter
        binding.listViewItems.adapter = adapter

        // Tambahkan aksi saat item di-list diklik
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = dataListWithDesc[position]
            val title = selectedItem["title"]
            val desc = selectedItem["desc"]

            // Menampilkan Toast sesuai instruksi modul
            Toast.makeText(requireContext(), "Kamu memilih: $title ($desc)", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
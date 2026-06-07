package com.example.farrelapps.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.farrelapps.data.AppDatabase
import com.example.farrelapps.data.entity.LanguageEntity
import com.example.farrelapps.databinding.FragmentMoreBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class MoreFragment : Fragment() {

    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: LanguageAdapter
    private lateinit var db: AppDatabase
    private val languages = mutableListOf<LanguageEntity>()

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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Daftar Bahasa"

        // Inisialisasi Database dan Adapter
        db = AppDatabase.getInstance(requireContext())
        adapter = LanguageAdapter(languages, this)

        binding.rvLanguages.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLanguages.adapter = adapter

        // Menambahkan garis pemisah antar item
        binding.rvLanguages.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )

        // Muat data (sekaligus isi data awal jika masih kosong)
        seedInitialData()

        // Tombol FAB tambah data
        binding.fabAddLanguage.setOnClickListener {
            showAddLanguageDialog()
        }
    }

    private fun seedInitialData() {
        lifecycleScope.launch {
            val data = db.languageDao().getAll()
            if (data.isEmpty()) {
                // Masukkan 3 data awal persis seperti di array sebelumnya
                val initialData = listOf(
                    LanguageEntity(title = "Kotlin", desc = "Bahasa untuk Android modern"),
                    LanguageEntity(title = "Java", desc = "Bahasa OOP yang populer"),
                    LanguageEntity(title = "Python", desc = "Bahasa yang mudah dipahami")
                )
                initialData.forEach { db.languageDao().insert(it) }
            }
            fetchLanguages()
        }
    }

    private fun fetchLanguages() {
        lifecycleScope.launch {
            val data = db.languageDao().getAll()
            languages.clear()
            languages.addAll(data)
            adapter.notifyDataSetChanged()
        }
    }

    fun deleteLanguage(language: LanguageEntity) {
        lifecycleScope.launch {
            db.languageDao().delete(language)
            fetchLanguages()
        }
    }

    // Menampilkan Form Pop-Up
    private fun showAddLanguageDialog() {
        val context = requireContext()
        val layout = LinearLayout(context).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 40, 50, 10)
        }

        val titleInput = EditText(context).apply { hint = "Nama Bahasa (ex: Dart)" }
        val descInput = EditText(context).apply { hint = "Deskripsi" }

        layout.addView(titleInput)
        layout.addView(descInput)

        MaterialAlertDialogBuilder(context)
            .setTitle("Tambah Bahasa Baru")
            .setView(layout)
            .setPositiveButton("Simpan") { dialog, _ ->
                val title = titleInput.text.toString()
                val desc = descInput.text.toString()

                if (title.isNotBlank() && desc.isNotBlank()) {
                    lifecycleScope.launch {
                        db.languageDao().insert(LanguageEntity(title = title, desc = desc))
                        fetchLanguages() // Refresh list
                    }
                } else {
                    Toast.makeText(context, "Input tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
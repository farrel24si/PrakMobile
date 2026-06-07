package com.example.farrelapps.More

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.farrelapps.data.entity.LanguageEntity
import com.example.farrelapps.databinding.ItemLanguageBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class LanguageAdapter(
    private val languages: List<LanguageEntity>,
    private val moreFragment: MoreFragment
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    inner class LanguageViewHolder(val binding: ItemLanguageBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageViewHolder {
        val binding = ItemLanguageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LanguageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LanguageViewHolder, position: Int) {
        val language = languages[position]
        holder.binding.tvLangTitle.text = language.title
        holder.binding.tvLangDesc.text = language.desc

        // Aksi klik tombol hapus
        holder.binding.btnDeleteLang.setOnClickListener {
            MaterialAlertDialogBuilder(holder.itemView.context)
                .setTitle("Hapus Bahasa")
                .setMessage("Yakin ingin menghapus '${language.title}'?")
                .setPositiveButton("Ya") { dialog, _ ->
                    moreFragment.deleteLanguage(language)
                    dialog.dismiss()
                }
                .setNegativeButton("Batal") { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
        }
    }

    override fun getItemCount(): Int = languages.size
}
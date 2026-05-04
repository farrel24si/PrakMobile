package com.example.farrelapps.Message

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.example.farrelapps.databinding.ItemMessageBinding
import com.google.android.material.snackbar.Snackbar

class MessageAdapter(
    context: Context,
    private val messages: List<MessageModel>
) : ArrayAdapter<MessageModel>(context, 0, messages) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // 1. Inisialisasi View Binding untuk layout item_message.xml
        val binding = ItemMessageBinding.inflate(LayoutInflater.from(context), parent, false)

        // 2. Ambil data pesan berdasarkan posisi saat ini
        val data = messages[position]

        // 3. Masukkan data teks ke komponen UI
        binding.textSender.text = data.senderName
        binding.textMessage.text = data.messageText

        // 4. Gunakan Glide untuk memuat gambar dari URL ke ImageView
        Glide.with(context)
            .load(data.avatarUrl)
            .into(binding.avatarImg)

        // 5. Tambahkan logika klik pada setiap item (Opsional sesuai modul)[cite: 1]
        binding.root.setOnClickListener {
            Snackbar.make(parent, "Pesan dari ${data.senderName}", Snackbar.LENGTH_SHORT).show()
        }

        // 6. Kembalikan tampilan yang sudah diisi data[cite: 1]
        return binding.root
    }
}
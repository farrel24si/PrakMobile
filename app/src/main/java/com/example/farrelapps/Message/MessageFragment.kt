package com.example.farrelapps.Message

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.farrelapps.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {

    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Mendefinisikan list data sesuai instruksi Modul 9
    private val messageList = listOf(
        MessageModel("Alya", "Halo! Apa kabar?", "https://avatar.iran.liara.run/public/1"),
        MessageModel("Budi", "Sudah makan?", "https://avatar.iran.liara.run/public/2"),
        MessageModel("Citra", "Jangan lupa tugasnya ya!", "https://avatar.iran.liara.run/public/3"),
        MessageModel("Dika", "Besok kita rapat jam 9", "https://avatar.iran.liara.run/public/4"),
        MessageModel("Eka", "Nice job kemarin!", "https://avatar.iran.liara.run/public/5"),
        MessageModel("Fajar", "Lagi ngapain?", "https://avatar.iran.liara.run/public/6"),
        MessageModel("Gita", "Boleh minta tolong?", "https://avatar.iran.liara.run/public/7"),
        MessageModel("Hana", "Lihat email ya", "https://avatar.iran.liara.run/public/8"),
        MessageModel("Irfan", "Oke noted", "https://avatar.iran.liara.run/public/9"),
        MessageModel("Joko", "Sampai jumpa besok", "https://avatar.iran.liara.run/public/10")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Menginisialisasi View Binding
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Memanggil Custom Adapter yang sudah kita buat sebelumnya
        val adapter = MessageAdapter(requireContext(), messageList)

        // Memasang adapter ke ListView
        binding.listMessageItems.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Membersihkan binding untuk mencegah kebocoran memori
        _binding = null
    }
}
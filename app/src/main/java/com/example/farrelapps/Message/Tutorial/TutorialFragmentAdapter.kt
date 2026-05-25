package com.example.farrelapps.Message.Tutorial

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class TutorialFragmentAdapter (
    activity: TutorialMessageActivity,
    private val fragments: List<Fragment>
) : FragmentStateAdapter(activity) {

    // Menentukan jumlah fragment yang akan ditampilkan
    override fun getItemCount(): Int {
        return fragments.size
    }

    // Menampilkan fragment sesuai dengan posisi/index-nya
    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}
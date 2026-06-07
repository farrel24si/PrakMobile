package com.example.farrelapps.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.farrelapps.data.dao.LanguageDao
import com.example.farrelapps.data.dao.NoteDao
import com.example.farrelapps.data.entity.LanguageEntity
import com.example.farrelapps.data.entity.NoteEntity

@Database(
    entities = [NoteEntity::class, LanguageEntity::class], // Mendaftarkan 2 tabel sekaligus
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun languageDao(): LanguageDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "farrel_database"
                )
                    .fallbackToDestructiveMigration() // Aman jika nanti ada perubahan struktur tabel
                    .build().also { INSTANCE = it }
            }
        }
    }
}
package com.example.farrelapps.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.farrelapps.data.entity.LanguageEntity

@Dao
interface LanguageDao {
    @Query("SELECT * FROM languages")
    suspend fun getAll(): List<LanguageEntity>

    @Insert
    suspend fun insert(language: LanguageEntity)

    @Delete
    suspend fun delete(language: LanguageEntity)
}
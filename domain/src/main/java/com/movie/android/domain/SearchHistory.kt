package com.movie.android.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class SearchHistory(
    val movieTitle: String = "",
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
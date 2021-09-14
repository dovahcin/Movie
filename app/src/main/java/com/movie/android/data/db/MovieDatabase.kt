package com.movie.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movie.android.domain.SearchHistory

@Database(entities = [SearchHistory::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun searchHistory(): SearchHistoryDao

}
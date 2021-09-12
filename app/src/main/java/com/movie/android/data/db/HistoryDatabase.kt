package com.movie.android.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.movie.android.domain.History

@Database(entities = [History::class], version = 1)
abstract class HistoryDatabase : RoomDatabase() {

    abstract fun searchHistory(): SearchHistory

}
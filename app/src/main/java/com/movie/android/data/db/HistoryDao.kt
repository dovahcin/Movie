package com.movie.android.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.movie.android.domain.History


@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(history: History)

    @Query("DELETE FROM history_table WHERE id = :titleId ")
    suspend fun delete(titleId: Int)

    @Query("SELECT * FROM history_table ORDER By id ASC")
    suspend fun getAllMovieTitles(): MutableList<History>


}